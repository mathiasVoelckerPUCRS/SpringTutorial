angular.module('app').directive('amigo', function(){
  return {
    scope: {
      usuario: "=usuario",
    },
    controller: function ($scope, $rootScope, $localStorage, $route, postagemService, usuarioService, authService, $route){

      $scope.isAmigo
      $scope.aguardandoResposta = false
      $scope.aguardandoAprovacao = false
      $scope.proprioUsuario = false


      $scope.aprovarSolicitacao = function(){
        usuarioService.aprovarSolicitacao($scope.amizade.usuarioSolicitado.id, $scope.usuario.id).then(
          function(response){
            console.log(response);
            alert("Amizade aprovada com sucesso1");
            $route.reload();
          },
          function(response){
            console.log(response);
            alert("Amizade aprovada com sucesso2");
            $route.reload();
          }
        );
      }

      $scope.enviarSolicitacao = function(){
        usuarioService.enviarSolicitacao($scope.usuario.id, $localStorage.usuarioLogado.id).then(
          function(response){
            alert("Solicitacao de amizade enviada")
            $route.reload()
          },
          function(response) {
            alert("Solicitacao de amizade enviada")
            $route.reload()
          }
        )
      }

      $scope.desfazerAmizade = function(){
        usuarioService.desfazerAmizade($localStorage.usuarioLogado.id, $scope.usuario.id).then(
          function(response){
            alert("Amizade desfeita")
            $route.reload()
          },
          function(response){
            alert("Amizade desfeita")
            $route.reload()
          }
        )
      }

    },
    templateUrl: 'directives/amigo/amigo.directive.html'
  }
});
