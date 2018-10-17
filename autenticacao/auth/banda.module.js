angular.module('auth').factory('bandaService', function (authConfig, $http, $q, $location, $localStorage, $rootScope) {

  let urlBanda = authConfig.urlBanda;

  function cadastrarBanda(banda){
    return $http.post(urlBanda + "registro", banda)
  }

  function buscarBandas(){
    return $http.get(urlBanda)
  }

  return {
    cadastrarBanda: cadastrarBanda,
    buscarBandas: buscarBandas
  }
})
