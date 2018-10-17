angular.module('auth').factory('postagemService', function (authConfig, $http, $q, $location, $localStorage, $rootScope) {

  let urlPostagemCadastro = authConfig.urlPostagemCadastro;
  let urlPostagem = authConfig.urlPostagem;
  let urlComentarioCadastro = authConfig.urlComentarioCadastro;
  let urlComentario = authConfig.urlComentario;

  function cadastrarPostagem(postagem){
    return $http.post(urlPostagemCadastro, postagem)
  }

  function listarPostagensDeUsuario(idUsuario){
    return $http.get(urlPostagem + "usuarios?usuarioId=" + idUsuario)
  }
  function listarPostagensDeUsuarioPorPagina(idUsuario, numPagina){
    return $http.get(urlPostagem + "usuarios?usuarioId=" + idUsuario + "&page=" + numPagina)
  }


  function listarPostagens(idUsuario){
    return $http.get(urlPostagem + "amigos?usuarioId=" + idUsuario)
  }
  function listarPostagensPorPagina(idUsuario, numPagina){
    return $http.get(urlPostagem + "amigos?usuarioId=" + idUsuario + "&page=" + numPagina)
  }

  function curtirPostagem(idUsuario, idPostagem){
    return $http.post(urlPostagem + "curtida?idUsuario=" + idUsuario + "&idPostagem=" + idPostagem)
  }

  function descurtirPostagem(idUsuario, idPostagem){
    return $http.delete(urlPostagem + "curtida?idUsuario=" + idUsuario + "&idPostagem=" + idPostagem)
  }

  function getComentarios(idPostagem){
    return $http.get(urlComentario + "?postagemId=" + idPostagem)
  }

  function criarComentario(comentario){
    return $http.post(urlComentarioCadastro, comentario)
  }


  return {
    cadastrarPostagem: cadastrarPostagem,
    listarPostagens: listarPostagens,
    listarPostagensPorPagina: listarPostagensPorPagina,
    curtirPostagem: curtirPostagem,
    descurtirPostagem: descurtirPostagem,
    getComentarios: getComentarios,
    criarComentario: criarComentario,
    listarPostagensDeUsuario: listarPostagensDeUsuario,
    listarPostagensDeUsuarioPorPagina: listarPostagensDeUsuarioPorPagina
  }
})
