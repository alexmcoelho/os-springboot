# os-springboot

## Sobre o que foi utilizado

### ModelMapper

Para fazer os DTOs foi utilizado *ModelMapper*, o que facilita muito na criação do código. O objetivo do *ModelMapper* é facilitar o mapeamento de objetos, determinando automaticamente como um modelo de objeto é mapeado para outro, com base em convenções, da mesma maneira que um ser humano faria - fornecendo uma API simples e segura para refatoração para lidar com casos de uso específicos. Disponível em http://modelmapper.org/.

### Spring Data JPA

O *Spring Data JPA* facilita a implementação fácil de repositórios baseados em *JPA*. Este módulo lida com suporte aprimorado para camadas de acesso a dados baseadas em *JPA*. Isso facilita a criação de aplicativos com tecnologia *Spring* que usam tecnologias de acesso a dados. 

A implementação de uma camada de acesso a dados de um aplicativo é complicada há bastante tempo. Muito código clichê precisa ser gravado para executar consultas simples, além de realizar paginação e auditoria. O *Spring Data JPA* visa melhorar significativamente a implementação das camadas de acesso a dados, reduzindo o esforço na quantidade realmente necessária. Como desenvolvedor, você escreve suas interfaces de repositório, incluindo métodos de localização personalizada, e o *Spring* fornecerá a implementação automaticamente. Disponível em https://spring.io/projects/spring-data-jpa.

### Flyway

*Flyway* é uma ferramenta de migração de banco de dados de código aberto. Favorece fortemente a simplicidade e a convenção sobre a configuração.

Ele é baseado em apenas 7 comandos básicos:  *Migrate*, *Clean*, *Info*, *Validate*, *Undo*, *Baseline* e *Repair*.

As migrações podem ser gravadas em SQL (sintaxe específica do banco de dados (como PL / SQL, T-SQL, ...) é suportada) ou Java (para transformações avançadas de dados ou para lidar com LOBs). Disponível em https://flywaydb.org/documentation/.