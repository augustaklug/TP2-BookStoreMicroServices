# Processo de Migração do Monolito para Microsserviços

## 1. Introdução
Este documento descreve o processo de migração da aplicação monolítica BookStore para uma arquitetura de microsserviços. A migração foi realizada com o objetivo de melhorar a escalabilidade, manutenibilidade e flexibilidade do sistema.
## 2. Análise do Monolito
### 2.1 Estrutura Original
O monolito original consistia em três principais funcionalidades:
•	Gestão de Livros
•	Gestão de Clientes
•	Gestão de Pedidos

### 2.2 Identificação de Bounded Contexts
Foram identificados três bounded contexts correspondentes às funcionalidades principais:
•	Contexto de Livros
•	Contexto de Clientes
•	Contexto de Pedidos

## 3. Estratégia de Migração
### 3.1 Abordagem Strangler Fig
Adotamos a abordagem Strangler Fig para migrar gradualmente as funcionalidades do monolito para microsserviços, começando pelo serviço de Pedidos.
### 3.2 Priorização
Serviço de Pedidos
Serviço de Clientes
Serviço de Livros

## 4. Arquitetura dos Microsserviços
### 4.1 Padrões Utilizados
Decomposição por Domínio de Negócio: Cada microsserviço representa um domínio de negócio específico.
Database per Service: Cada microsserviço possui seu próprio banco de dados.

### 4.2 Tecnologias Utilizadas
Spring Boot para desenvolvimento dos microsserviços
H2 Database para armazenamento de dados (para simplificar o desenvolvimento inicial)
Maven para gerenciamento de dependências e build

## 5. Decisões de Design
### 5.1 Isolamento de Contextos
Cada microsserviço foi projetado para ser autônomo, encapsulando sua própria lógica de negócio e dados.
### 5.2 Comunicação entre Serviços
Optamos por uma comunicação síncrona via REST API entre os serviços para simplificar a implementação inicial.

## 6. Implementação dos Microsserviços
### 6.1 Serviço de Pedidos
Responsável por criar, ler, atualizar e deletar pedidos
Comunica-se com os serviços de Clientes e Livros para validar informações
### 6.2 Serviço de Clientes
Gerencia informações dos clientes
Fornece API para criar, ler, atualizar e deletar clientes
### 6.3 Serviço de Livros
Gerencia o catálogo de livros
Fornece API para criar, ler, atualizar e deletar livros

## 7. Comunicação entre Microsserviços
### 7.1 REST API
Os serviços se comunicam através de chamadas REST síncronas.

## 8. Conclusão
A migração para microsserviços trouxe benefícios significativos em termos de escalabilidade e manutenibilidade. Embora tenha introduzido novos desafios, a arquitetura resultante é mais flexível e adequada para evolução futura do sistema BookStore.
