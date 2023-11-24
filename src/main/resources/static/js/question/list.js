$(document).ready(function (){
    $('.createdAt').each(function() {
        var createdAt = $(this).text();
        var formattedCreatedAt = formatCreatedAt(createdAt);
        $(this).text(formattedCreatedAt);
    });

    $(".detail").click(function (){
        var questionId = $(this).find("input:eq(0)").val();
        var subjectId = $(this).find("input:eq(1)").val();

        console.log(questionId);
        console.log(subjectId);

        $.ajax({
            url: "/subjects/" + subjectId + "/questions/" + questionId,
            type: "GET",
            success: function (response) {
                location.href = "/subjects/" + subjectId + "/questions/" + questionId;
            },
            error:function(request, status, error){
                console.log("code:"+request.status+"\n"+"error:"+error);
            }

        });
    });
});

function formatCreatedAt(createdAt) {
    var date = new Date(createdAt);
    var year = date.getFullYear();
    var month = ('0' + (date.getMonth() + 1)).slice(-2);
    var day = ('0' + date.getDate()).slice(-2);
    var hours = ('0' + date.getHours()).slice(-2);
    var minutes = ('0' + date.getMinutes()).slice(-2);
    return year + '-' + month + '-' + day + ' ' + hours + '시 ' + minutes + '분';
}
