angular.module('app').controller('LoginController', function ($scope, $location, $localStorage, authService, usuarioService) {

  $scope.login = function (usuario) {

    authService.login(usuario)
      .then(
        function (response) {
          console.log("1")
          console.log(response);
          alert('Login com sucesso!');
          usuarioService.buscarUsuarioPorEmail(response.data.email).then(
            function(response){
              console.log("response no auht")
              console.log(response)
              $localStorage.usuarioLogado = response.data;
              $location.path("/")
              // $localStorage.usuarioLogado.data_nascimento = formatarData($localStorage.usuarioLogado.data_nascimento);
            })
        },
        function (response) {
          console.log(response);
          alert('Usuario ou senha invalidos!');
        });
  };

});
