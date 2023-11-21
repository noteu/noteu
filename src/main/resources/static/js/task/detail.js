$(document).ready(function() {
    const div_edit = document.getElementById("div_edit");
    const div_delete = document.getElementById("div_delete");

    var taskId = document.getElementById("taskId").value;
    var subjectId = document.getElementById("subjectId").value;

    console.log(taskId)

    div_delete.addEventListener("click", () => {
        $.ajax({
            type: "POST",
            url: `/subjects/${subjectId}/tasks/${taskId}`,
            data: {
                "taskId": taskId
            },
            datatype: "JSON",
            success: function(data) {
                alert("삭제가 완료되었습니다.");
                location.href=`/subjects/${taskId}/tasks`;
            },
            error: function(error) {
                alert("삭제에 실패되었습니다.");
            },
        });
    });

    div_edit.addEventListener("click", () => {
        location.href = `/subjects/${subjectId}/tasks/${taskId}/edit-form`;
    })
});