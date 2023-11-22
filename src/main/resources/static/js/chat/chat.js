window.onload = function () {
    findAllRoom();
    messageInput = document.getElementById('message-input');
    senderId = document.getElementById("memberId").value;
    senderName = document.getElementById("memberName").value;
    sendBtn = document.getElementById('send-btn');
    sendBtn.onclick = sendMessage;
    chatMessage = document.getElementById('chat-message');
};
let chatMessage;
let selectRoomId;

async function findAllRoom() {

    let subjectId = document.getElementById("subjectId").value;
    let loginMemberId = document.getElementById("memberId").value;
    let token = document.cookie.split("=")[1];

    await axios
        .get(`/subjects/${subjectId}/chats/rooms?loginMemberId=${loginMemberId}`, {
            headers: {
                Authorization: `${token}`
            }
        })
        .then(response => {
            console.log(response);
            console.log(response.data);
            const rooms = response.data.chatRoomResponseDtos; // 채팅 방 배열
            const loginId = response.data.loginId; // 채팅 방 배열

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
                const roomId = room.id;
                const participants = room.participants; // 참가자 배열
                const email = participants[0].email;
                const friendId = participants[0].id;
                const profile = participants[0].profile;
                const tel = participants[0].tel;
                const username = participants[0].username;
                const lastMessage = room.lastMessage;
                const parsedTime = room.lastMessageDateTime;

                console.log(roomId)
                console.log(participants);

                const a = document.createElement("a");
                a.href = "javascript:void(0);";
                a.className = "text-body";

                const div2 = document.createElement("div");
                div2.className = "d-flex align-items-start mt-1 p-2";
                div2.onclick = function () {
                    selectRoomId = roomId;
                    toggleMessageStyle(this);
                    memberDetailHead(subjectId, friendId, email, profile, tel, username);
                    memberDetailBody(friendId, email, profile, tel, username);
                    pastChat(roomId, friendId, loginId);
                };

                const innerDiv = document.createElement("div");
                innerDiv.className = "w-100 overflow-hidden";

                const h5 = document.createElement("h5");
                h5.className = "mt-0 mb-0 font-14";
                h5.textContent = username;

                const span1 = document.createElement("span");
                const date = new Date(parsedTime);
                const hours = date.getHours();
                const minutes = date.getMinutes();
                const ampm = hours >= 12 ? 'pm' : 'am';
                const twelveHourFormat = (hours % 12) || 12;
                const formattedTime = twelveHourFormat + ':' + minutes + ampm;
                console.log("parsedTime : " + parsedTime);
                console.log("formattedTime : " + formattedTime);
                span1.id = `time${roomId}`;
                span1.className = "float-end text-muted font-12";
                span1.textContent = formattedTime;

                const p = document.createElement("p");
                p.className = "mt-1 mb-0 text-muted font-14";

                const span2 = document.createElement("span");
                span2.className = "w-25 float-end text-end";

                const span3 = document.createElement("span");
                span3.className = "badge badge-danger-lighten";
                span3.textContent = "2";

                const span4 = document.createElement("span");
                span4.id = `lastmessage${roomId}`;
                console.log("span4 id : " + roomId);
                span4.className = "w-75";
                span4.textContent = lastMessage;

                cardDiv.appendChild(a);
                a.appendChild(div2);
                div2.appendChild(innerDiv);
                innerDiv.appendChild(h5);
                h5.appendChild(span1);
                innerDiv.appendChild(p);
                p.appendChild(span2);
                span2.appendChild(span3);
                p.appendChild(span4);
                console.log("p태그에 span을 붙힙니다.")

                connect(roomId, senderName);
            });

            rowDiv.appendChild(colDiv);
            console.log("rowdiv태그에 coldiv을 붙힙니다.");
            colDiv.appendChild(cardDiv);
            console.log("coldiv태그에 carddiv을 붙힙니다.");

            // 생성한 HTML 요소를 기존의 HTML에 추가
            document.querySelector("#users").appendChild(rowDiv);
            console.log("마지막으로 html에 추가합니다.");

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

function memberDetailHead(subjectId, friendId, email, profile, tel, username) {
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
    button.onclick = function () {
        createChatRoom(subjectId, memberId);
    };
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

function createChatRoom(subjectId, friendId) {
    axios.post(`/subjects/${subjectId}/chats/rooms`, {friendId: friendId})
        .then(response => {
            console.log(response);
            console.log("방 생성!!");
        })
        .catch(response => {
            console.log(response);
            console.log("이미 방이 존재합니다!!");
        });
}

function memberDetailBody(friendId, email, profile, tel, username) {
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


// 채팅 불러오기
function pastChat(roomId, friendId, loginId) {
    const subjectId = 1;
    const token = document.cookie.split("=")[1];

    axios
        .get(`/subjects/${subjectId}/chats/rooms/api?roomId=${roomId}`, {
            headers: {
                Authorization: `${token}`
            }
        })
        .then(response => {
            const chats = response.data; // 채팅 방 배열
            console.log(chats);

            document.querySelector("#chat-message").innerHTML = '';

            chats.forEach(chat => {
                let date = new Date(chat.createdAt);
                let hours = date.getHours();
                let minutes = date.getMinutes().toString().padStart(2, '0');
                let parsedTime = `${hours}:${minutes}`;

                const li = document.createElement("li");
                if (chat.senderId === loginId) {
                    li.className = "clearfix odd";
                } else {
                    li.className = "clearfix";
                }

                const chatAvatar = document.createElement("div");
                const time = document.createElement("i");
                chatAvatar.className = "chat-avatar";
                time.textContent = parsedTime;

                const conversationText = document.createElement("div");
                const ctextWrap = document.createElement("div");
                const i = document.createElement("i");
                const p = document.createElement("p");
                conversationText.className = "conversation-text";
                ctextWrap.className = "ctext-wrap";
                i.textContent = chat.senderName;
                p.textContent = chat.message;

                li.appendChild(chatAvatar);
                chatAvatar.appendChild(time);
                li.appendChild(conversationText);
                conversationText.appendChild(ctextWrap);
                ctextWrap.appendChild(i);
                ctextWrap.appendChild(p);
                document.querySelector("#chat-message").appendChild(li);
            });
        });
}

////////////////////////////////////// socket chat ////////////////////////////////////////////

let messageInput;
let sendBtn;
let senderId;
let senderName;
let reconnect = 0;
let sock;
let ws

function sendMessage() {
    ws.send("/app/chat/message", {}, JSON.stringify({
        messageType: 'TALK',
        roomId: selectRoomId,
        senderId: senderId,
        senderName: senderName,
        message: messageInput.value
    }));
    messageInput.value = '';
}

function recvMessage(recv) {
    if (recv.roomId === selectRoomId) {
        let date = new Date(recv.createdAt);
        let hours = date.getHours();
        let minutes = date.getMinutes().toString().padStart(2, '0');
        let parsedTime = `${hours}:${minutes}`;

        const li = document.createElement("li");

        if (recv.senderName === senderName) {
            li.className = "clearfix odd";
        } else {
            li.className = "clearfix";
        }

        const chatAvatar = document.createElement("div");
        const time = document.createElement("i");
        chatAvatar.className = "chat-avatar";
        time.textContent = parsedTime;

        const conversationText = document.createElement("div");
        const ctextWrap = document.createElement("div");
        const i = document.createElement("i");
        const p = document.createElement("p");
        conversationText.className = "conversation-text";
        ctextWrap.className = "ctext-wrap";
        i.textContent = recv.senderName;
        p.textContent = recv.message;

        li.appendChild(chatAvatar);
        chatAvatar.appendChild(time);
        li.appendChild(conversationText);
        conversationText.appendChild(ctextWrap);
        ctextWrap.appendChild(i);
        ctextWrap.appendChild(p);

        document.querySelector("#chat-message").appendChild(li);
    }

    // 실시간으로 메시지온거 그려주기
    const date = new Date(recv.createdAt);
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const ampm = hours >= 12 ? 'pm' : 'am';
    const twelveHourFormat = (hours % 12) || 12;
    const formattedTime = twelveHourFormat + ':' + minutes + ampm;
    console.log(`log 확인 : time${recv.roomId}`);
    console.log(`log 확인 : lastmessage${recv.roomId}`);

    document.getElementById(`time${recv.roomId}`).innerHTML = formattedTime;
    document.getElementById(`lastmessage${recv.roomId}`).innerHTML = recv.message;
}

function connect(roomId, senderName) {
    console.log("소켓 연결 시작");
    sock = new SockJS("/ws/chat");
    ws = Stomp.over(sock);

    // 연결 시도 함수
    function tryConnect() {
        console.log("연결 시도 중...");
        sock = new SockJS("/ws/chat");
        ws = Stomp.over(sock);
        connect(roomId, senderName); // 다시 connect 함수 호출
    }

    ws.connect({}, function () {
        setTimeout(function () {
            ws.subscribe("/topic/chats/room/" + roomId, function (message) {
                console.log("메시지를 받았어요!~~~~~~~~~");
                const recv = JSON.parse(message.body);
                recvMessage(recv);
            });
            ws.send("/app/chat/message", {}, JSON.stringify({
                messageType: 'ENTER',
                id: roomId,
                sender: senderName
            }));
        }, 1000);

        // 성공적인 연결 시 재연결 횟수를 리셋
        reconnect = 0;
    }, function (error) {
        if (reconnect++ <= 5) {
            console.log("연결 실패");
            setTimeout(tryConnect, 10 * 1000); // 10초 후에 다시 시도
        } else {
            console.log("연결 재시도 횟수 초과");
        }
    });
}
