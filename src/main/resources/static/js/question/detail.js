$(document).ready(function() {
    // SimpleMDE 초기화
    var contentElement = $("#content")[0];
    var simplemde = contentElement ? new SimpleMDE({element: contentElement}) : null;

    // SimpleMDE의 내용이 변경될 때마다 HTML로 변환하여 표시
    if (simplemde) {
        simplemde.codemirror.on("change", function() {
            var content = simplemde.markdown(simplemde.value());
            $("#contentPreview").html(content);
        });
    }

    $("#submitBtn").click(function (){
        var subjectId = $("#subjectId").val();
        var questionPostId = $("#questionPostId").val();
        var questionComment = $("#questionComment").val();

        $.ajax({
            url: "/subjects/" + subjectId + "/questions/" + questionPostId + "/question-comment",
            type:"POST",
            data:{"questionCommentContent": questionComment},
            success:function (response) {
                location.reload();
            },
            error:function () {
                console.log("데이터 전송 실패");
            }
        })
    });

    $("#deleteBtn").on("click", function () {
        var subjectId = $("#subjectId").val();
        var questionPostId = $("#questionPostId").val();
        var questionCommentId = $("#questionPostCommentId").val();

        $.ajax({
            url: "/subjects/" + subjectId + "/questions/" + questionPostId + "/delete/" + questionCommentId,
            type:"GET",
            success:function (response){
                location.reload();
            },
            error:function () {
                console.log("데이터 전송 실패");
            }
        })
    });
});