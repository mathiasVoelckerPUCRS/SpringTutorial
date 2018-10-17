angular.module('app').directive('bandaLista', function(){
  return {
    scope: {
      banda: "=banda",
    },
    controller: function ($scope, $rootScope, $localStorage, $route, postagemService, usuarioService, authService, $route){
      $scope.banda
    },
    templateUrl: 'directives/banda/banda.directive.html'
  }
});
