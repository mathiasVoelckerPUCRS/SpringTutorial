angular.module('app').controller('PerfilController', function ($scope, $localStorage, $location, $route, $routeParams, postagemService, usuarioService, competenciaService, authService) {

  $scope.paginacao = new Object();
  $scope.paginacao.pagina = 0;
  $scope.isAmigo
  $scope.aguardandoResposta = false
  $scope.aguardandoAprovacao = false
  $scope.proprioUsuario = false

  usuarioService.buscarUsuarioPorId($routeParams.idUsuario).then(
    function (response) {
      $scope.usuario = response.data
      $scope.usuario.data_nascimento = formatarData($scope.usuario.data_nascimento)
      console.log($scope.usuario)
      $scope.buscarPostagens($scope.usuario.id);
      $scope.buscarCompetencias()
      conferirAmizade();
    })

    function conferirAmizade(){
      usuarioService.conferirAmizade($localStorage.usuarioLogado.id, $scope.usuario.id).then(
        function(response){
          console.log("amizade")
          console.log(response.data)
          $scope.amizade = response.data
          $scope.amizade = response.data
          if($scope.usuario.id == $localStorage.usuarioLogado.id){
            $scope.proprioUsuario = true
          }
          else{
            if($scope.amizade == ""){
              $scope.isAmigo = false
            }
            else{
              $scope.isAmigo = true
              if(!$scope.amizade.aprovado){
                if($scope.amizade.usuarioSolicitado.id == $localStorage.usuarioLogado.id){
                  $scope.aguardandoAprovacao = true
                }
                else{
                  $scope.aguardandoResposta = true
                }
              }
            }
          }
        }
      )
    }


    $scope.buscarPostagens = function (id){
      postagemService.listarPostagensDeUsuario(id).then(
        function(response){
          response.data.content.forEach(function(postagem){
            $scope.postagens.push(postagem)
            $scope.postagens.sort(function(a,b){
              return a.dataPostagem - b.dataPostagem
            });
          })
          console.log("postagens")
          console.log($scope.postagens)
          $scope.paginacao.totalPaginas = response.data.totalPages
        }
      );
    }

    $scope.postagens = [];




    $scope.buscarPostagensProximaPagina = function (id){
      $scope.paginacao.pagina++;
      postagemService.listarPostagensDeUsuarioPorPagina(id, $scope.paginacao.pagina).then(
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
      postagemService.listarPostagensDeUsuarioPorPagina(id, $scope.paginacao.pagina).then(
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

    $scope.enviarSolicitacao = function(){
      usuarioService.enviarSolicitacao($localStorage.usuarioLogado.id, $scope.usuario.id).then(
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

    //COMPETENCIAS
    $scope.buscarCompetencias = function(){
      competenciaService.buscarCompetenciaPorUsuario($scope.usuario.id).then(
        function(response){
          console.log(response)
          $scope.competencias = response.data
        }
      )
    }

    function formatarData(timestamp){
      var date = new Date(timestamp)
      var retorno =  date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear()
      return retorno
    }

  });
