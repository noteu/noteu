let req = new XMLHttpRequest();
var previousPasswordResult = false;
var passwordResult = false;
let msg = ""
function passwordChange() {
    const previousPassword = $("#previous_password").val();
    const newPassword = $("#new_password").val();
    $.ajax({
        url: "/members/pw-check",
        type: "POST",
        data: {"previousPassword": previousPassword, "newPassword": newPassword},
        dataType: "text",
        success: function (data) {
            console.log(data);
            if (data === "1") {
                console.log("이전 비밀번호 일치");
                previousPasswordResult = true;
                submit();
            } else if (data === "0") {
                console.log("이전 비밀번호 불일치");
                previousPasswordResult = false;
                submit();
            }
        },
        error: function () {
            console.log("비밀번호 일치 확인 ajax 요청 실패");
        }
    });
}

const submit = () => {
    if (!previousPasswordResult) {
        msg = "이전 비밀번호가 일치하지 않습니다."
        const form = document.querySelector("form");
        form.addEventListener("submit", (event) => {
            event.preventDefault();
            console.log(event.target);
        });
        const msgDiv = document.getElementById("msgDiv");
        msgDiv.style.display = "block";
        document.getElementById("msg").innerHTML = msg
    } else if (!(previousPasswordResult && passwordResult)) {
        msg = "입력 항목을 확인해주세요."
        const $form = document.querySelector("form");
        $form.addEventListener("submit", (event) => {
            event.preventDefault();
            console.log(event.target);
        });
        const msgDiv = document.getElementById("msgDiv");
        msgDiv.style.display = "block";
        document.getElementById("msg").innerHTML = msg
    } else {
        const memberId = $("#memberId").val();
        const previousPassword = $("#previous_password").val();
        const newPassword = $("#new_password").val();
        $.ajax({
            url: "/members/password/" + memberId,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "id": memberId,
                "previousPassword": previousPassword,
                "newPassword": newPassword
            }),
            success: function () {
                location.href = "/members/account/" + memberId;
            },
            error: function () {
                console.log("POST /members/password error");
            },
        });
    }

}
const setMessage = (elementId, message, color) => {
    const element = document.getElementById(elementId);
    element.style.color = color;
    element.innerHTML = message;
};

const passwordCheck = () => {
    const newPassword = f.newPassword.value;
    const passwordConfirm = f.passwordConfirm.value;

    if (!new RegExp("^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$").test(newPassword)) {
        setMessage("password_msg", "영어, 숫자, 특수문자를 포함하여 8자 이상, 15자 이하로 입력해주세요.", "red");
    } else if (newPassword === passwordConfirm) {
        setMessage("password_msg", "비밀번호와 비밀번호 확인이 일치합니다.", "green");
        passwordResult = true;
    } else {
        setMessage("password_msg", "비밀번호와 비밀번호 확인이 일치하지 않습니다.", "red");
    }
};
