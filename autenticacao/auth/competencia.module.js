angular.module('auth').factory('competenciaService', function (authConfig, $http, $q, $location, $localStorage, $rootScope) {

  let urlInstrumento = authConfig.urlInstrumento;
  let urlCompetencia = authConfig.urlCompetencia;

  function buscarInstrumentos(){
    return $http.get(urlInstrumento)
  }

  function buscarCompetenciaPorUsuario(id){
    return $http.get(urlCompetencia + "usuario?idUsuario=" + id)
  }

  function cadastrarCompetencia(competencia){
    return $http.post(urlCompetencia+"registro", competencia)
  }

  return{
    buscarInstrumentos: buscarInstrumentos,
    cadastrarCompetencia: cadastrarCompetencia,
    buscarCompetenciaPorUsuario: buscarCompetenciaPorUsuario
  }
})
