angular.module('app').controller('ContatosController', function ($scope, $localStorage, $location, $route, usuarioService, authService) {

  $scope.usuario = $localStorage.usuarioLogado
  $scope.usuarios
  $scope.paginacao = new Object();
  $scope.paginacao.pagina;

  $scope.buscarUsuarios = function(){
    usuarioService.buscarUsuarios().then(
      function(response){
        $scope.usuarios = response.data.content
        console.log("alert")
        console.log($scope.usuarios)
        $scope.paginacao.totalPaginas = response.data.totalPages
        $scope.paginacao.pagina = 0;
      }
    )
  }

  $scope.buscarUsuarios()

  $scope.buscarPorLogin = function(busca){
    usuarioService.buscarUsuarioPorLogin(busca).then(
      function(response){
        console.log(response)
        $scope.usuarios = response.data
      }
    )
  }

  $scope.buscarPorEmail = function(busca){
    $scope.usuarios = []
    usuarioService.buscarUsuarioPorEmail(busca).then(
      function(response){
        console.log(response)
        $scope.usuarios.push(response.data)
      }
    )
  }

  $scope.buscarContatosProximaPagina = function (id){
    $scope.paginacao.pagina++;
    usuarioService.buscarUsuarioPorPagina($scope.paginacao.pagina).then(
      function(response){
        console.log(response)
        $scope.usuarios = []
        response.data.content.forEach(function(usuario){
          $scope.usuarios.push(usuario)
        })
        $scope.paginacao.totalPaginas = response.data.totalPages
      }
    );
  }

  $scope.buscarContatosPaginaAnterior = function (id){
    $scope.paginacao.pagina--;
    usuarioService.buscarUsuarioPorPagina($scope.paginacao.pagina).then(
      function(response){
        console.log(response)
        $scope.usuarios = []
        response.data.content.forEach(function(usuario){
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
