angular.module('auth').factory('usuarioService', function (authConfig, $http, $q, $location, $localStorage, $rootScope) {

  let urlUsuarioCadastro = authConfig.urlUsuarioCadastro;
  let urlUsuario = authConfig.urlUsuario;

  function buscarUsuarios(){
    return $http.get(urlUsuario+"/list")
  }

  function buscarUsuarioPorPagina(numPagina){
    return $http.get(urlUsuario + "/list?page=" + numPagina)
  }

  function cadastrarUsuario(usuario){
    return $http.post(urlUsuarioCadastro, usuario)
  }

  function editarUsuario(usuario){
    return $http.put(urlUsuarioCadastro + "/" + usuario.id, usuario)
  }

  function buscarUsuarioPorId(id){
    return $http.get(urlUsuario + id)
  }

  function buscarUsuarioPorEmail(email){
    return $http.get(urlUsuario + "email?email=" + email)
  }

  function buscarUsuarioPorLogin(login){
    return $http.get(urlUsuario + "login?login=" + login)
  }

  function buscarSolicitacoes(id){
    return $http.get(urlUsuario + "solicitacoes?id=" + id, id)
  }

  function buscarAmigos(idUsuario){
    console.log(idUsuario)
    return $http.get(urlUsuario + "amigos?id=" + idUsuario)
  }

  function buscarAmigosPorPagina(idUsuario, numPagina){
    return $http.get(urlUsuario + "amigos?id=" + idUsuario + "&page=" + numPagina)
  }

  function conferirAmizade(idUsuarioSolicitante, idUsuarioSolicitado){
    return $http.get(urlUsuario + "amigo?idUsuarioSolicitante=" + idUsuarioSolicitante + "&idUsuarioSolicitado=" + idUsuarioSolicitado)
  }

  function aprovarSolicitacao(idUsuarioSolicitante, idUsuarioSolicitado){
    var url = urlUsuario + "aprovar?idSolicitante=" + idUsuarioSolicitante + "&idSolicitado=" + idUsuarioSolicitado
    return $http.put(url, idUsuarioSolicitante, idUsuarioSolicitado)
  }

  function enviarSolicitacao(idUsuarioSolicitante, idUsuarioSolicitado){
    return $http.post(urlUsuario + "amigos?idSolicitante=" + idUsuarioSolicitante + "&idSolicitado=" + idUsuarioSolicitado)
  }

  function desfazerAmizade(idUsuarioSolicitante, idUsuarioSolicitado){
    return $http.delete(urlUsuario + "desfazerAmizade?idSolicitante=" + idUsuarioSolicitante + "&idSolicitado=" + idUsuarioSolicitado)
  }

  return {
    cadastrarUsuario: cadastrarUsuario,
    buscarUsuarioPorEmail: buscarUsuarioPorEmail,
    buscarSolicitacoes: buscarSolicitacoes,
    aprovarSolicitacao: aprovarSolicitacao,
    editarUsuario: editarUsuario,
    buscarUsuarioPorId: buscarUsuarioPorId,
    buscarUsuarios: buscarUsuarios,
    buscarUsuarioPorLogin: buscarUsuarioPorLogin,
    buscarUsuarioPorEmail: buscarUsuarioPorEmail,
    buscarUsuarioPorPagina: buscarUsuarioPorPagina,
    conferirAmizade: conferirAmizade,
    enviarSolicitacao: enviarSolicitacao,
    desfazerAmizade: desfazerAmizade,
    buscarAmigos: buscarAmigos,
    buscarAmigosPorPagina: buscarAmigosPorPagina
  }
})
