# Documentación de Pruebas Unitarias para ReadingServiceImpl

Este documento contiene los detalles de las pruebas unitarias realizadas para el servicio de lecturas.

## Pruebas

### Prueba: Obtener todas las lecturas (Método: getAllReadings)

- **Descripción**: Verificar que el método `getAllReadings` devuelva correctamente todas las lecturas.
- **Entradas**: Ninguna.
- **Resultado Esperado**:
  - El método debe devolver una lista de objetos `ReadReadingDto` con todas las lecturas almacenadas en la base de datos.
  
#### Casos de prueba

1. **Lecturas disponibles**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: La lista debe contener todas las lecturas registradas en la base de datos.
   - **Resultado obtenido**: Se obtuvo la lista de todas las lecturas correctamente.

2. **Sin lecturas disponibles**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: Se debe devolver una lista vacía si no hay lecturas registradas.
   - **Resultado obtenido**: Se devolvió una lista vacía, como se esperaba.

---

### Prueba: Obtener lecturas por ID de usuario (Método: getReadingsByUserId)

- **Descripción**: Verificar que el método `getReadingsByUserId` devuelva las lecturas correspondientes a un usuario específico.
- **Entradas**:
  - `idUser`: ID del usuario para buscar sus lecturas (por ejemplo, `1`).
- **Resultado Esperado**:
  - El método debe devolver una lista de objetos `ReadReadingDto` que correspondan a las lecturas del usuario con el `idUser` proporcionado.
  
#### Casos de prueba

1. **Lecturas encontradas para un usuario**:
   - **Entrada**: `idUser = 1`.
   - **Resultado esperado**: La lista debe contener las lecturas asociadas al usuario con `idUser = 1`.
   - **Resultado obtenido**: La lista contiene las lecturas correspondientes al usuario con `idUser = 1`.

2. **No se encuentran lecturas para un usuario**:
   - **Entrada**: `idUser = 9999` (ID que no existe).
   - **Resultado esperado**: Se debe devolver una lista vacía si no se encuentran lecturas para el usuario.
   - **Resultado obtenido**: Se devolvió una lista vacía, como se esperaba.

---

### Prueba: Crear lectura (Método: createReading)

- **Descripción**: Verificar que el método `createReading` cree correctamente una nueva lectura para un usuario.
- **Entradas**:
  - `idUser`: ID del usuario para crear la lectura (por ejemplo, `1`).
  - `readingDto`: Objeto `ReadingDto` con los detalles de la lectura, como `reading`, `idPeriod`, etc.
- **Resultado Esperado**:
  - Si la residencia y el medidor se encuentran, se debe crear una nueva lectura con los datos proporcionados.
  - Si no se encuentra la residencia o el medidor, se debe lanzar una excepción `RuntimeException` con el mensaje adecuado.
  
#### Casos de prueba

1. **Creación de lectura exitosa**:
   - **Entrada**: `idUser = 1`, `readingDto = {reading: 123.45, idPeriod: 1}`.
   - **Resultado esperado**: Se debe crear una nueva lectura con los datos proporcionados. La lectura debe ser registrada correctamente en la base de datos con el `idUserRegister` y `idUserUpdate` correspondientes.
   - **Resultado obtenido**: La lectura fue creada correctamente en la base de datos con los valores esperados y los campos de auditoría fueron actualizados correctamente.

2. **Residencia no encontrada**:
   - **Entrada**: `idUser = 9999`, `readingDto = {reading: 123.45, idPeriod: 1}`.
   - **Resultado esperado**: Se debe lanzar una excepción `RuntimeException` con el mensaje "Residencia no encontrada para el usuario".
   - **Resultado obtenido**: Se lanzó la excepción `RuntimeException` con el mensaje "Residencia no encontrada para el usuario".

3. **Medidor no encontrado**:
   - **Entrada**: `idUser = 1`, `readingDto = {reading: 123.45, idPeriod: 1}`.
   - **Resultado esperado**: Se debe lanzar una excepción `RuntimeException` con el mensaje "Medidor no encontrado para la residencia".
   - **Resultado obtenido**: Se lanzó la excepción `RuntimeException` con el mensaje "Medidor no encontrado para la residencia".

---

### Prueba: Actualizar lectura (Método: updateReading)

- **Descripción**: Verificar que el método `updateReading` actualice correctamente una lectura existente.
- **Entradas**:
  - `idReading`: ID de la lectura a actualizar.
  - `readingDto`: Objeto `ReadingDto` con los nuevos valores de la lectura.
- **Resultado Esperado**:
  - Si la lectura con `idReading` existe, se deben actualizar los campos `reading`, `idPeriod`, `idUserUpdate` y `dateUpdate` de la lectura.
  - Si no se encuentra la lectura, se debe lanzar una excepción `RuntimeException` con el mensaje adecuado.
  
#### Casos de prueba

1. **Actualización exitosa**:
   - **Entrada**: `idReading = 1`, `readingDto = {reading: 150.00, idPeriod: 2}`.
   - **Resultado esperado**: La lectura con `idReading = 1` debe ser actualizada con los nuevos valores de `reading`, `idPeriod`, `idUserUpdate` y `dateUpdate`.
   - **Resultado obtenido**: La lectura fue actualizada correctamente con los nuevos valores y el campo `dateUpdate` se actualizó con la fecha y hora actual.

2. **Lectura no encontrada**:
   - **Entrada**: `idReading = 9999`, `readingDto = {reading: 150.00, idPeriod: 2}`.
   - **Resultado esperado**: Se debe lanzar una excepción `RuntimeException` con el mensaje "Lectura no encontrada para actualización".
   - **Resultado obtenido**: Se lanzó la excepción `RuntimeException` con el mensaje "Lectura no encontrada para actualización".

---
