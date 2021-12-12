# Compass.Uol - Sprint 3 - Avaliação
Resolução da avaliação proposta na sprint 3 do programa de bolsas da Compass.Uol
## Swagger
O arquivo no formato yaml encontra-se na pasta Swagger na raiz do projeto
## API Rest
### Endpoints
- POST - /api/states
- GET - /api/states
- GET - /api/states/{id}
- PUT - /api/states/{id}
- DELETE - /api/states/{id}

### Estrutura do objeto
```
{ 
"id": 1, 
"nome": "Rio Grande do Norte", 
"regiao": "Nordeste", 
"populacao": 3409000, 
"capital": "Natal", 
"area": 52.797 
}
```
### Filtro e ordenação
#### GET
##### Filtro por região
```http://localhost:8080/api/states?regiao=REGIAO```
###### Opções para filtro por regiao
- NORTE
- NORDESTE
- SUDESTE
- SUL
- CENTRO_OESTE
##### Ordenar por Estados com maior população
```http://localhost:8080/api/states?sort=populacao```
##### Ordenar por Estados com maior area
```http://localhost:8080/api/states?sort=area```
