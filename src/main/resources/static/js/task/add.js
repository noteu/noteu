const btn_submit = document.getElementById("btn_submit");

$(document).ready(function () {
    $('#deadLine').datepicker({
        format: 'd-M-yyyy',
        autoclose: true
    });
});

btn_submit.addEventListener("click", () => {
    var subjectId = document.getElementById("subjectId").value;

    var taskTitle = document.getElementById("taskTitle").value;
    var taskContent = document.getElementById("taskContent").value;
    
    var deadLineValue = document.getElementById("deadLineInput").value;
    // var formattedDate = new Date(deadLineValue).toISOString();

    // JavaScript Date 객체를 생성
    var date = new Date(deadLineValue);

    // 브라우저의 로컬 타임존을 고려하여 서버로 전송할 문자열로 변환
    var formattedDate = date.toLocaleString('ko-KR', { timeZone: 'Asia/Seoul' });

    $.ajax({
        type: "POST",
        url: `/subjects/${subjectId}/tasks`,
        data: {
            "taskTitle": taskTitle,
            "taskContent": taskContent,
            "deadLine": formattedDate,
        },
        datatype: "JSON",
        success: function(data) {
            alert("과제 등록 완료");
            location.href=`/subjects/${subjectId}/tasks`;
        },
        error: function(error) {
            alert("과제 등록 실패");
            console.log(formattedDate);
        },
    });
});
