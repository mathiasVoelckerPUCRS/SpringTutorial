angular.module('app', ['ngRoute', 'auth']);

// Configurações utilizadas pelo módulo de autenticação (authService)
angular.module('app').constant('authConfig', {

    // Obrigatória - URL da API que retorna o usuário

    //urlUsuario: 'http://10.99.0.12:3296/api/acessos/usuarioLogado',
    //urlUsuario: 'http://10.99.0.24/AutDemo.WebApi/api/acessos/usuariologado',

    urlUsuario: 'http://localhost:9090/usuario/',

    urlUsuarioCadastro: 'http://localhost:9090/usuario/registro',

    urlPostagemCadastro: 'http://localhost:9090/postagem/registro',

    urlPostagem: 'http://localhost:9090/postagem/',

    urlComentario: 'http://localhost:9090/comentario/',

    urlComentarioCadastro: 'http://localhost:9090/comentario/registro',

    urlInstrumento: 'http://localhost:9090/instrumento/',

    urlCompetencia: 'http://localhost:9090/competencia/',

    urlBanda: 'http://localhost:9090/banda/',

    // Obrigatória - URL da aplicação que possui o formulário de login
    urlLogin: '/login',

    // Opcional - URL da aplicação para onde será redirecionado (se for informado) após o LOGIN com sucesso
    urlPrivado: '/privado',

    // Opcional - URL da aplicação para onde será redirecionado (se for informado) após o LOGOUT
    urlLogout: '/login'
});
