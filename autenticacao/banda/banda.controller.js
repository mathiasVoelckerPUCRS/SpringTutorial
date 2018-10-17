angular.module('app').controller('BandaController', function ($scope, $localStorage, $location, $route, postagemService, usuarioService, competenciaService, bandaService) {

  $scope.usuario = $localStorage.usuarioLogado
  $scope.usuarios
  $scope.usuariosSelecao
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

  $scope.cadastrarBanda = function(banda){
    banda.id_usuarios = []
    banda.id_usuarios.push($localStorage.usuarioLogado.id)
    $scope.usuarios.forEach(function(usuarioSelecao){
      if(usuarioSelecao.selected){
        $scope.banda.id_usuarios.push(usuarioSelecao.id)
      }
    })
    console.log(banda)
    bandaService.cadastrarBanda(banda).then(
      function(response){
        console.log(response)
        alert("Banda cadastrada com sucesso")
      }
    )
  }


  bandaService.buscarBandas().then(
    function(response){
      console.log(response.data.content)
      $scope.bandas = response.data.content
    }
  )

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



  $scope.buscarAmigos()
})
