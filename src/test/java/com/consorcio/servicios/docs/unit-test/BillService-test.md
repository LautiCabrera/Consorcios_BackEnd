# Documentación de Pruebas Unitarias para BillServiceImpl

Este documento contiene los detalles de las pruebas unitarias realizadas para el servicio de las tarifas.

## Pruebas

### Prueba: Obtener todas las facturas (Método: getAllBill)

- **Descripción**: Verificar que el método `getAllBill` devuelva correctamente la lista de todas las facturas disponibles.
- **Entradas**: Ninguna.
- **Resultado Esperado**:
  - El método debe devolver una lista de objetos `Bill` que contenga todas las facturas almacenadas en la base de datos. Si no hay facturas, debe devolver una lista vacía.

#### Casos de prueba

1. **Facturas encontradas**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: El método debe devolver una lista de facturas.
   - **Resultado obtenido**: Se obtuvo correctamente una lista de facturas.

2. **Sin facturas disponibles**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: Si no existen facturas en la base de datos, el método debe devolver una lista vacía.
   - **Resultado obtenido**: Se devolvió una lista vacía, como se esperaba.

---

### Prueba: Obtener factura por ID (Método: getBillById)

- **Descripción**: Verificar que el método `getBillById` devuelva la factura correspondiente al `id` proporcionado.
- **Entradas**: Un `idBill` válido.
- **Resultado Esperado**:
  - El método debe devolver la factura correspondiente al `idBill`. Si no existe, debe devolver `null`.

#### Casos de prueba

1. **Factura encontrada**:
   - **Entrada**: `idBill = 1`.
   - **Resultado esperado**: El método debe devolver la factura con `id = 1`.
   - **Resultado obtenido**: Se devolvió la factura correctamente.

2. **Factura no encontrada**:
   - **Entrada**: `idBill = 999`.
   - **Resultado esperado**: El método debe devolver `null` ya que no existe una factura con ese `id`.
   - **Resultado obtenido**: El método devolvió `null`, como se esperaba.

---

### Prueba: Generación de factura (Método: generateBill)

- **Descripción**: Verificar que el método `generateBill` cree una factura correctamente cuando se proporciona un `idUser` y `idPeriod` válidos.
- **Entradas**: `idUser = 1`, `idPeriod = 2024-11`.
- **Resultado Esperado**:
  - El método debe generar y almacenar una nueva factura en la base de datos.

#### Casos de prueba

1. **Factura generada correctamente**:
   - **Entrada**: `idUser = 1`, `idPeriod = 2024-11`.
   - **Resultado esperado**: Se debe generar una factura para el usuario con `id = 1` y el `idPeriod = 2024-11`.
   - **Resultado obtenido**: La factura fue generada correctamente y almacenada en la base de datos.

2. **Error en la generación de factura**:
   - **Entrada**: `idUser = 1`, `idPeriod = 999`.
   - **Resultado esperado**: El método debe lanzar una excepción si no se encuentran lecturas anteriores o actuales.
   - **Resultado obtenido**: Se lanzó la excepción correctamente, indicando que las lecturas no fueron encontradas.

---

### Prueba: Generar facturas para todos los medidores (Método: generateBillForAllMeters)

- **Descripción**: Verificar que el método `generateBillForAllMeters` genere facturas para todos los usuarios activos.
- **Entradas**: `idPeriod = 2024-11`.
- **Resultado Esperado**:
  - El método debe generar facturas para todos los usuarios activos y enviar una salida por consola indicando el progreso.

#### Casos de prueba

1. **Generación de facturas para todos los usuarios**:
   - **Entrada**: `idPeriod = 2024-11`.
   - **Resultado esperado**: Se deben generar facturas para todos los usuarios con rol `ROLE_USER` y estado `ACTIVE`.
   - **Resultado obtenido**: Las facturas fueron generadas correctamente para todos los usuarios activos.

2. **Error al generar la factura de un usuario**:
   - **Entrada**: `idPeriod = 2024-11`.
   - **Resultado esperado**: Si ocurre un error al generar la factura para un usuario, el error debe ser capturado y registrado en la consola.
   - **Resultado obtenido**: El error fue capturado correctamente, y se imprimió el mensaje de error en la consola.

---

### Prueba: Cambiar el estado de pago de una factura (Método: changePaymentStatusByBillId)

- **Descripción**: Verificar que el método `changePaymentStatusByBillId` cambie el estado de pago de una factura de `false` a `true`.
- **Entradas**: `idBill = 1`.
- **Resultado Esperado**:
  - El estado de pago de la factura debe cambiar a `true` después de ejecutar el método.

#### Casos de prueba

1. **Cambio de estado de pago**:
   - **Entrada**: `idBill = 1`.
   - **Resultado esperado**: El estado de pago de la factura debe cambiar a `true`.
   - **Resultado obtenido**: El estado de pago de la factura fue cambiado correctamente.

---

