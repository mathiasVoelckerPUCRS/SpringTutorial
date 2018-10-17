angular.module('app')
.directive('cwiHeader', function (authService, $rootScope) {

  return {

    restrict: 'E',

    scope: {},

    templateUrl: 'directives/header/header.directive.html',

    controller: function ($scope, authService, $location, $localStorage) {

      atualizarUsuario();

      $scope.logout = authService.logout;

      $rootScope.$on('authLoginSuccess', function () {
        atualizarUsuario();
      });

      $rootScope.$on('authLogoutSuccess', function () {
        atualizarUsuario();
      });

      function atualizarUsuario() {
        $scope.usuario = authService.getUsuario();
      }

      $scope.auth = authService;
      $scope.usuarioLogado
      $scope.usuarioLogado = $localStorage.usuarioLogado


      estaLogado();
      $scope.estaLogado = estaLogado

      function estaLogado(){
        if($scope.auth.getUsuario() != undefined){
          return true;
        }
        return false
      }


    }
  }

});
