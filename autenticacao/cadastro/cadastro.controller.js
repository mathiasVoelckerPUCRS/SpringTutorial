angular.module('app').controller('CadastroController', function ($scope, $localStorage, $routeParams, usuarioService) {

  $scope.isEdicao = false
  $scope.mensagemPrincipal = "Bem vindo ao MusicBook"
  $scope.botaoTexto = "Cadastrar-se"

  if($routeParams.idUsuario != undefined){
    console.log("entrou")
    $scope.isEdicao = true
    $scope.mensagemPrincipal = "Editar Usuario"
    $scope.botaoTexto = "Editar"
    usuarioService.buscarUsuarioPorId($routeParams.idUsuario).then(
      function (response) {
        $scope.usuario = response.data
        $scope.ConfirmacaoSenha = $scope.usuario.senha
        $scope.usuario.data_nascimento = new Date($scope.usuario.data_nascimento)
        console.log($scope.usuario)
      })
    }



    $scope.cadastrar = function (usuario) {
      if($scope.formCadastro.$valid){
        if($routeParams.idUsuario == undefined){
          usuarioService.cadastrarUsuario(usuario).then(
            function(response){
              console.log(response);
              alert('Cadastro com sucesso!');
            },
            function (response) {
              console.log(response);
              alert(response.data.localizedMessage);
            });
          }
          else{
            console.log("vai editar")
            console.log(usuario)
            usuarioService.editarUsuario(usuario).then(
              function(response){
                // $localStorage.usuarioLogado.username = response.data.email;
                console.log("teste")
                console.log(response);
                $localStorage.usuarioLogado = response.data
                alert('Edicao com sucesso!');
              },
              function (response) {
                console.log(response);
                alert(response.data.localizedMessage);
              });
            }
          }
        }
      });
