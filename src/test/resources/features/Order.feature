@Order
Feature: Gestión de órdenes en PetStore

  Como usuario del sistema,
  Quiero poder crear y consultar órdenes de compra,
  Para gestionar pedidos de mascotas en la tienda.
  @crearOrder
  Scenario Outline: Crear una nueva orden en PetStore
    Given que el servicio PetStore está disponible en "https://petstore.swagger.io/v2/"
    When creo la orden con orderId <orderId>, petId <petId>, quantity <quantity>, shipDate "<shipDate>" y status "<status>"
    Then valido que la respuesta sea <statusCode>
    And valido que la orden sea creada con los datos enviados orderId <orderId> petId <petId>, quantity <quantity>, shipDate "<shipDate>" y status "<status>"

    Examples:
      | orderId  | petId  | quantity | shipDate                     | status | statusCode |
      | 101      | 10     | 8        | 2025-01-25T20:59:09.684+0000 | placed | 200        |
      | 102      | 11     | 5        | 2025-01-28T20:59:09.684+0000 | placed | 200        |
      | 103      | 12     | 15       | 2025-01-29T20:59:09.684+0000 | placed | 200        |

  @consultarOrder
  Scenario Outline: Consultar una orden existente en PetStore
    Given que el servicio PetStore está disponible en "https://petstore.swagger.io/v2/"
    When consulto la orden con ID <orderId>
    Then valido que la respuesta sea <statusCode>
    And valido que la orden sea creada con los datos enviados orderId <orderId> petId <petId>, quantity <quantity>, shipDate "<shipDate>" y status "<status>"

    Examples:
      | orderId  | petId  | quantity | shipDate                     | status | statusCode |
      | 101      | 10     | 8        | 2025-01-25T20:59:09.684+0000 | placed | 200        |
      | 102      | 11     | 5        | 2025-01-28T20:59:09.684+0000 | placed | 200        |
      | 103      | 12     | 15       | 2025-01-29T20:59:09.684+0000 | placed | 200        |
