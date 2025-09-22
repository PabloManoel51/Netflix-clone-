# Sistema Streaming

Sistema em Java que simula uma plataforma de streaming de filmes e séries, com gerenciamento de usuários, planos de assinatura, pagamentos e fila de pedidos.

## **Funcionalidades**

- **Conteúdos (Filmes e Séries)**
  - Cadastro, listagem e busca por gênero ou temporadas.
- **Usuários**
  - Cadastro, listagem e busca por nome.
- **Planos**
  - Cadastro, listagem e busca por tipo de plano.
- **Pagamentos**
  - Registro de pagamentos por usuário, listagem e consulta.
- **Fila de Pedidos**
  - Gerenciamento de pedidos em fila (adicionar, atender, listar).

## **Arquivos do Projeto**

- `Main.java` – Arquivo principal com o menu de execução.
- `ConteudoNegocio.java` – Classes de Filme, Série, repositórios, serviços e controllers.
- `UsuarioNegocio.java` – Classes de Usuário, serviço e controller.
- `PlanoNegocio.java` – Classes de Plano, serviço e controller.
- `PagamentoNegocio.java` – Classes de Pagamento, serviço e controller.
- `FilaNegocio.java` – Classe de gerenciamento de fila.

