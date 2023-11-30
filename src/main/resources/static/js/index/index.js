$(document).ready(function (){
   $("#join").on("click", function (){
      location.href = "/auth/sign-up";
   });

   $("#login").on("click", function (){
      location.href = "/auth/login";
   });
});