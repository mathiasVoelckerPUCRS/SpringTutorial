angular.module('app').directive('postagem', function(){
  return {
    scope: {
      post: "=post",
    },
    controller: function ($scope, $rootScope, $localStorage, $route, postagemService, usuarioService, authService, $route){

      $scope.comentario = new Object();

      $scope.usuario = $localStorage.usuarioLogado
      $scope.usuario.data_nascimento = formatarData($scope.usuario.data_nascimento)
      $scope.numCurtidas = $scope.post.curtidas.length
      $scope.curtida = estaCurtido()

      $scope.data = formatarData($scope.post.dataPostagem)
      postagemService.getComentarios($scope.post.id).then(
        function(response){
          console.log(response.data)
          $scope.comentarios = response.data
        }
      )

      $scope.comentarPostagem = function(comentario){
       comentario.id_usuario = $scope.usuario.id
       comentario.id_postagem = $scope.post.id
       console.log(comentario)
       postagemService.criarComentario(comentario).then(
         function(response){
           console.log(response)
           $route.reload()
         }
       )
     }



      $scope.curtir = function(){
        postagemService.curtirPostagem($scope.post.usuario.id, $scope.post.id).then(
          function(reponse){
            $scope.numCurtidas++;
          }
        )
      }

      $scope.descurtir = function(){
        postagemService.descurtirPostagem($scope.post.usuario.id, $scope.post.id).then(
          function(reponse){
            $scope.numCurtidas--;
          }
        )
      }

      function estaCurtido(){
        var retorno = false
        $scope.post.curtidas.forEach(function(curtida){
          if(curtida.id == $scope.usuario.id){
            retorno = true;
          }
        })
        return retorno;
      }


      function formatarData(timestamp){
        var date = new Date(timestamp)
        var retorno =  date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear()
        return retorno
      }

    },
    templateUrl: 'directives/postagem/postagem.directive.html'
  }
});
