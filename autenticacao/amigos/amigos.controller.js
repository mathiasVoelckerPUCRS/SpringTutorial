angular.module('app').controller('AmigosController', function ($scope, $localStorage, $location, $route, usuarioService, authService) {

  $scope.usuario = $localStorage.usuarioLogado
  $scope.usuarios
  $scope.paginacao = new Object();
  $scope.paginacao.pagina;
  

  $scope.buscarAmigos = function(){
    usuarioService.buscarAmigos($scope.usuario.id).then(
      function(response){
        $scope.usuarios = response.data
        console.log("alert")
        console.log(response)
        $scope.paginacao.totalPaginas = response.data.totalPages
        $scope.paginacao.pagina = 0;
      }
    )
  }

  $scope.buscarAmigos()

  $scope.buscarAmigosProximaPagina = function (id){
    $scope.paginacao.pagina++;
    usuarioService.buscarAmigosPorPagina($scope.usuario.id, $scope.paginacao.pagina).then(
      function(response){
        console.log(response)
        $scope.usuarios = []
        response.data.forEach(function(usuario){
          $scope.usuarios.push(usuario)
        })
        $scope.paginacao.totalPaginas = response.data.totalPages
      }
    );
  }

  $scope.buscarAmigosPaginaAnterior = function (id){
    $scope.paginacao.pagina--;
    usuarioService.buscarAmigosPorPagina($scope.usuario.id, $scope.paginacao.pagina).then(
      function(response){
        console.log(response)
        $scope.usuarios = []
        response.data.forEach(function(usuario){
          $scope.usuarios.push(usuario)
        })
        $scope.paginacao.totalPaginas = response.data.totalPages
      }
    );
  }

  $scope.conferirPrimeiraPagina = function(){
    if($scope.paginacao.pagina == 0){
      return true
    }
  }

  $scope.conferirUltimaPagina = function(){
    if($scope.paginacao.pagina == ($scope.paginacao.totalPaginas - 1)){
      return true
    }
  }


});
