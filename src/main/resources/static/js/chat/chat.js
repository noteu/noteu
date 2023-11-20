window.onload = function () {
    findAllRoom();
};

function findAllRoom() {

    let subjectId = document.getElementById("subjectId").value;
    let loginMemberId = document.getElementById("memberId").value;
    let token = document.cookie.split("=")[1];

    axios
        .get(`/subjects/${subjectId}/chats/rooms?loginMemberId=${loginMemberId}`, {
            headers: {
                Authorization: `${token}`
            }
        })
        .then(response => {
            const rooms = response.data; // 채팅 방 배열

            // 기존 html 초기화
            document.querySelector("#users").innerHTML = '';

            const rowDiv = document.createElement("div");
            rowDiv.className = "row";

            const colDiv = document.createElement("div");
            colDiv.className = "col";

            const cardDiv = document.createElement("div");
            cardDiv.className = "card-body py-0";
            cardDiv.style.maxHeight = "565px";
            cardDiv.setAttribute("data-simplebar", "");


            // 각 방에 대한 정보를 반복하여 HTML 요소를 생성
            rooms.forEach(room => {
                const participants = room.participants; // 참가자 배열

                const email = participants[0].email;
                const memberId = participants[0].id;
                const profile = participants[0].profile;
                const tel = participants[0].tel;
                const username = participants[0].username;

                console.log(participants);

                const a = document.createElement("a");
                a.href = "javascript:void(0);";
                a.className = "text-body";

                const div2 = document.createElement("div");
                div2.className = "d-flex align-items-start mt-1 p-2";
                div2.onclick = function() {
                    toggleMessageStyle(this);
                    memberDetailHead(memberId, email, profile, tel, username);
                    memberDetailBody(memberId, email, profile, tel, username);
                };

                const innerDiv = document.createElement("div");
                innerDiv.className = "w-100 overflow-hidden";

                const h5 = document.createElement("h5");
                h5.className = "mt-0 mb-0 font-14";
                h5.textContent = username;

                const span1 = document.createElement("span");
                span1.className = "float-end text-muted font-12";

                const p = document.createElement("p");
                p.className = "mt-1 mb-0 text-muted font-14";

                const span2 = document.createElement("span");
                span2.className = "w-25 float-end text-end";

                const span3 = document.createElement("span");
                span3.className = "badge badge-danger-lighten";
                span3.textContent = "2";

                const span4 = document.createElement("span");
                span4.className = "w-75";
                span4.textContent = "최근온 메시지를 표시할거에요 >_ㅇ";

                cardDiv.appendChild(a);
                a.appendChild(div2);
                div2.appendChild(innerDiv);
                innerDiv.appendChild(h5);
                h5.appendChild(span1);
                innerDiv.appendChild(p);
                p.appendChild(span2);
                span2.appendChild(span3);
                p.appendChild(span4)
            });

            rowDiv.appendChild(colDiv);
            colDiv.appendChild(cardDiv);

            // 생성한 HTML 요소를 기존의 HTML에 추가
            document.querySelector("#users").appendChild(rowDiv);
        });
}

function toggleMessageStyle(element) {
    // 모든 요소의 클래스를 초기화
    var elements = document.getElementsByClassName('d-flex align-items-start p-2');
    for (var i = 0; i < elements.length; i++) {
        elements[i].classList.remove('bg-light');
    }

    // 클릭한 요소에만 활성화 클래스 추가
    element.classList.add('bg-light');
}

function memberDetailHead(memberId, email, profile, tel, username) {
    // 기존 html 초기화하기
    document.querySelector("#user-detail-head").innerHTML = '';

    const div = document.createElement("div");
    div.className = "mt-3 text-center";

    const img = document.createElement("img");
    img.className = "img-thumbnail avatar-lg rounded-circle";
    img.src = profile;
    img.alt = username;

    const h4 = document.createElement("h4");
    h4.textContent = username;

    const button = document.createElement("button");
    const i = document.createElement("i");
    button.className = "btn btn-primary btn-sm mt-1"
    i.className = "uil ri-chat-new-line me-1";
    i.textContent = "send chat"

    const p = document.createElement("p")
    const strong = document.createElement("strong");
    p.className = "text-muted mt-2 font-14";
    p.textContent = "마지막 상호작용: ";
    strong.textContent = "몇 시간 전"

    div.appendChild(img);
    div.appendChild(h4);
    div.appendChild(button);
    button.appendChild(i);
    div.appendChild(p);
    p.appendChild(strong);

    document.querySelector("#user-detail-head").appendChild(div);
}

function memberDetailBody(memberId, email, profile, tel, username) {
    // 기존 html 초기화하기
    document.querySelector("#user-detail-body").innerHTML = '';

    const div = document.createElement("div");
    div.className = "mt-3";

    const hr = document.createElement("hr");
    hr.className = "";

    const pEmail1 = document.createElement("p");
    const strongEmail = document.createElement("strong");
    const emailTextNode = document.createTextNode(' Email:');
    const iEmail = document.createElement("i");
    const pEmail2 = document.createElement("p");
    pEmail1.className = "mt-4 mb-1";
    iEmail.className = "uil uil-at";
    pEmail2.textContent = email;

    const pPhone1 = document.createElement("p");
    const strongPhone = document.createElement("strong");
    const phoneTextNode = document.createTextNode(' Phone Number:');
    const iPhone = document.createElement("i");
    const pPhone2 = document.createElement("p");
    pPhone1.className = "mt-3 mb-1";
    iPhone.className = "uil uil-phone";
    pPhone2.textContent = tel;

    div.appendChild(hr);
    div.appendChild(pEmail1);
    pEmail1.appendChild(strongEmail);
    strongEmail.appendChild(iEmail);
    strongEmail.appendChild(emailTextNode);
    div.appendChild(pEmail2);

    div.appendChild(pPhone1);
    pPhone1.appendChild(strongPhone);
    strongPhone.appendChild(iPhone);
    strongPhone.appendChild(phoneTextNode);
    div.appendChild(pPhone2);

    document.querySelector("#user-detail-body").appendChild(div);
}
