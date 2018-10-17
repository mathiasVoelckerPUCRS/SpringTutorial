angular.module('app').config(function ($routeProvider) {

  $routeProvider

    // pública
    .when('/home', {
      controller: 'HomeController',
      templateUrl: 'home/home.html'
    })

    // pública
    .when('/login', {
      controller: 'LoginController',
      templateUrl: 'login/login.html'
    })

    // pública
    .when('/cadastro', {
      controller: 'CadastroController',
      templateUrl: 'cadastro/cadastro.html'
    })

    .when('/cadastro/:idUsuario?', {
      controller: 'CadastroController',
      templateUrl: 'cadastro/cadastro.html'
    })


    .when('/contatos', {
      controller: 'ContatosController',
      templateUrl: 'contatos/contatos.html'
    })

    .when('/amigos', {
      controller: 'AmigosController',
      templateUrl: 'amigos/amigos.html'
    })

    .when('/banda', {
      controller: 'BandaController',
      templateUrl: 'banda/banda.html'
    })

    .when('/perfil/:idUsuario', {
      controller: 'PerfilController',
      templateUrl: 'perfil/perfil.html'
    })


    // pública
    .when('/', {
      controller: 'DashboardController',
      templateUrl: 'dashboard/dashboard.html'
    })


    // privado
    .when('/privado', {
      controller: 'PrivadoController',
      templateUrl: 'privado/privado.html',
      resolve: {

        // define que para acessar esta página deve ser um usuário autenticado (mas não restringe o tipo de permissão)
        autenticado: function (authService) {
          return authService.isAutenticadoPromise();
        }
      }
    })

    .otherwise('/');
});
