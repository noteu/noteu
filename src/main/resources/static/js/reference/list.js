$(document).ready(function (){
   $(".detail").click(function (){
       var referenceId = $(this).find("input:eq(0)").val();
       var subjectId = $(this).find("input:eq(1)").val();

       console.log(referenceId);
       console.log(subjectId);

       $.ajax({
          url: "/subjects/" + subjectId + "/references/" + referenceId,
          type: "GET",
          success: function (response) {
              location.href = "/subjects/" + subjectId + "/references/" + referenceId;
          },
           error:function(request, status, error){
               console.log("code:"+request.status+"\n"+"error:"+error);
           }

       });
   });
});