# Diagrama UML — Sistema Redes de Padaria

## Modelo de domínio

```mermaid
classDiagram
class Insumo {
  -int id
  -String nome
  -String unidadeMedida
  -double quantidade
  +cadastrar()
  +atualizar(nome, unidadeMedida, quantidade)
  +consultar() double
}
class Estoque {
  -int id
  -double quantidadeAtual
  -List~Insumo~ insumos
  +adicionarInsumo(Insumo)
  +registrarEntrada(insumoId, quantidade)
  +registrarSaida(insumoId, quantidade)
  +consultarQuantidade() double
}
class Unidade {
  -int id
  -String nome
  -String endereco
  +cadastrar()
  +atualizar(nome, endereco)
  +consultarEstoque() double
}
class Movimentacao {
  -int id
  -String tipoMovimentacao
  -double quantidade
  -Date data
}
class DemandaReposicao {
  -int id
  -Date dataSolicitacao
  -String status
  +criarDemanda()
  +atualizarStatus(status)
  +listarInsumos() List~Insumo~
}

Estoque "1" o-- "many" Insumo : insumos
Unidade "1" --> "1" Estoque : estoque
Movimentacao "many" --> "1" Insumo : insumo
Movimentacao "many" --> "1" Estoque : estoque
DemandaReposicao "many" --> "1" Unidade : unidade
DemandaReposicao "1" o-- "many" Insumo : insumos
```

## Arquitetura em camadas (exemplo: Insumo)

> O mesmo padrão (Controller → Service → Repository) se repete para
> Estoque, Unidade, Movimentação e Demanda de Reposição.

```mermaid
classDiagram
class SistemaView {
  +iniciar()
}
class InsumoController {
  +cadastrarInsumo(Insumo)
  +consultarInsumo(id) Insumo
  +listarInsumos() List
}
class InsumoService {
  +cadastrar(Insumo)
  +consultar(id) Insumo
  +listarTodos() List
}
class InsumoRepository {
  <<interface>>
  +salvar(Insumo)
  +buscarPorId(id) Insumo
  +listarTodos() List
}
class InsumoRepositoryMemoria
class InsumoRepositorySQLite
class RepositoryFactory {
  +criarInsumoRepository() InsumoRepository
}
class MovimentacaoStrategy {
  <<interface>>
  +executar(Estoque, id, quantidade)
}
class EntradaStrategy
class SaidaStrategy

SistemaView --> InsumoController : usa
InsumoController --> InsumoService : usa
InsumoService --> InsumoRepository : usa
InsumoRepositoryMemoria ..|> InsumoRepository : implementa
InsumoRepositorySQLite ..|> InsumoRepository : implementa
RepositoryFactory ..> InsumoRepositoryMemoria : cria
RepositoryFactory ..> InsumoRepositorySQLite : cria
EntradaStrategy ..|> MovimentacaoStrategy : implementa
SaidaStrategy ..|> MovimentacaoStrategy : implementa
```
