'use strict';

app.controller('ProdutoController', ['$scope','ProdutoService', '$window', function ($scope,ProdutoService, $window) {
    $scope.produtos = {};
    $scope.produto = {nomeProduto:"", quantidade:0, precoProduto:0.0};

    $scope.getAllProdutos = function () {
        ProdutoService.getAllProdutos()
            .then(function success(response){
                debugger;
                    $scope.produtos = response.data;
                },
                function error (response){
                    $scope.errorMessage = 'Erro ao lista produtos!';
                });
    };

    $scope.getProduto = function (id) {
        ProdutoService.getProduto(id)
            .then(function success(response){
                    $scope.produto = response.data;
                },
                function error (response ){
                    $scope.errorMessage = "Erro ao selecionar produto!";
                });
    }

    $scope.addProduto = function () {
        if ($scope.produto != null && $scope.produto.nomeProduto) {
            ProdutoService.addProduto($scope.produto)
                .then (function success(response){
                        $scope.message = 'Produto salvo!';
                        $scope.errorMessage = '';
                        $scope.reloadPage();
                    },
                    function error(response){
                        $scope.errorMessage = 'Erro ao salvar produto!';
                    });
        }
        else {
            $scope.errorMessage = 'Por favor inserir nome para o produto!';
        }
    };

    $scope.updateProduto = function () {
        ProdutoService.updateProduto($scope.produto.idProduto, $scope.produto)
            .then(function success(response){
                    $scope.message = 'Produto atualizado';
                },
                function error(response){
                    $scope.errorMessage = 'Error updating user!';
                });
    }

    $scope.deleteProduto = function (id) {
        ProdutoService.deleteProduto(id)
            .then (function success(response){
                    $scope.message = 'Produto deletado!';
                    $scope.produto = null;
                },
                function error(response){
                    $scope.errorMessage = 'Erro ao deletar produto!';
                })
    };

    $scope.getAllProdutos();

    $scope.reloadPage = function()
    {
        $window.location.reload();
    }

}]);