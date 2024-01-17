self.addEventListener('fetch', function(event) {
  event.respondWith(
    caches.match(event.request)
      .then(function(response) {
        if (response) {
          // Se o recurso estiver no cache, retorna-o
          return response;
        }
        // Caso contrário, busca na rede e armazena em cache para uso futuro
        return fetch(event.request).then(function(response) {
          return caches.open('seu-cache').then(function(cache) {
            cache.put(event.request, response.clone());
            return response;
          });
        });
      })
  );
});