const del = () => {
    const memberId = document.getElementById("delete_member").value;
    console.log(memberId);
    if (confirm("정말로 탈퇴하시겠습니까?")) {
        location.href = "/members/delete/" + memberId;
    } else {
        alert("탈퇴가 취소되었습니다.");
        return;
    }
}
