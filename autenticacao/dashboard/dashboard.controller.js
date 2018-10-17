angular.module('app').controller('DashboardController', function ($scope, $localStorage, $location, $route, postagemService, usuarioService, competenciaService, authService) {

  $scope.paginacao = new Object();
  $scope.paginacao.pagina = 0;
  console.log("usuario logado");
  console.log($localStorage.usuarioLogado)
  $scope.usuario = $localStorage.usuarioLogado;
  $scope.usuario.data_nascimento = formatarData($scope.usuario.data_nascimento);

  $scope.buscarPostagens = function (id){
    postagemService.listarPostagens(id).then(
      function(response){
        response.data.content.forEach(function(postagem){
          $scope.postagens.push(postagem)
        })
        console.log("postagens")
        console.log($scope.postagens)
        $scope.paginacao.totalPaginas = response.data.totalPages
      }
    );
  }

  $scope.postagens = [];
  buscarSolicitacoes($scope.usuario.id);
  $scope.buscarPostagens($scope.usuario.id);

  $scope.aprovarSolicitacao = function(idUsuarioSolicitante){
    usuarioService.aprovarSolicitacao(idUsuarioSolicitante, $scope.usuario.id).then(
      function(response){
        console.log(response);
        alert("Amizade aprovada com sucesso");
        $route.reload();
      }
    );
  }

  $scope.cadastrarPostagem = function(postagem){
    postagem.idUsuario = $scope.usuario.id
    postagemService.cadastrarPostagem(postagem).then(
      function(response){
        console.log(response)
        alert("Postagem cadastrada com sucesso");
      }
    )
  }

  function buscarSolicitacoes(id){
    usuarioService.buscarSolicitacoes(id).then(
      function(response){
        console.log(response.data)
        $scope.solicitacoes = response.data
      }
    )
  }

  $scope.buscarPostagensProximaPagina = function (id){
    $scope.paginacao.pagina++;
    postagemService.listarPostagensPorPagina(id, $scope.paginacao.pagina).then(
      function(response){
        $scope.postagens = []
        response.data.content.forEach(function(postagem){
          $scope.postagens.push(postagem)
          $scope.postagens.sort(function(a,b){
            return a.dataPostagem - b.dataPostagem
          });
        })
        console.log("postagens")
        console.log($scope.postagens.reverse())
        $scope.paginacao.totalPaginas = response.data.totalPages
      }
    );
  }

  $scope.buscarPostagensPaginaAnterior = function (id){
    $scope.paginacao.pagina--;
    postagemService.listarPostagensPorPagina(id, $scope.paginacao.pagina).then(
      function(response){
        $scope.postagens = []
        response.data.content.forEach(function(postagem){
          $scope.postagens.push(postagem)
          $scope.postagens.sort(function(a,b){
            return a.dataPostagem - b.dataPostagem
          });
        })
        console.log("postagens")
        console.log($scope.postagens.reverse())
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

  function formatarData(timestamp){
    var date = new Date(timestamp)
    var retorno =  date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear()
    return retorno
  }

  // INSTRUMENTOS
  competenciaService.buscarInstrumentos().then(
    function(response){
      console.log("instrumentos")
      console.log(response)
      $scope.instrumentos = response.data
    }
  )

  $scope.cadastrarCompetencia = function(competencia){
    competencia.id_usuario = $scope.usuario.id
    competencia.id_instrumento = parseInt(competencia.id_instrumento)
    console.log(competencia)
    competenciaService.cadastrarCompetencia(competencia)
    $route.reload();
  }

  competenciaService.buscarCompetenciaPorUsuario($scope.usuario.id).then(
    function(response){
      console.log(response)
      $scope.competencias = response.data
    }
  )


});
