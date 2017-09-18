'use strict';

app.service('ProdutoService',['$http', function ($http) {

    this.getAllProdutos = function getAllProdutos(){
        return $http({
            method: 'GET',
            url: 'http://localhost:8080/produtos'
        });
    };

    this.getProduto = function getProduto(idProduto){
        return $http({
            method: 'GET',
            url: 'http://localhost:8080/produto/'+idProduto
        });
    };

    this.addProduto = function addProduto(produto){
        return $http({
            method: 'POST',
            url: 'http://localhost:8080/salvarProduto',
            data: {
                "nomeProduto": produto.nomeProduto,
                "quantidade": produto.quantidade,
                "precoProduto": produto.precoProduto
            }
        });
    };

    this.deleteProduto = function deleteProduto(id){
        return $http({
            method: 'DELETE',
            url: 'http://localhost:8080/removerProduto/'+id
        })
    }

}]);