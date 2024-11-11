# Documentación de Pruebas Unitarias para ModalityServiceImpl

Este documento contiene los detalles de las pruebas unitarias realizadas para el servicio de modalidades.

## Pruebas

### Prueba: Obtener todas las modalidades (Método: getAllModalities)

- **Descripción**: Verificar que el método `getAllModalities` devuelva correctamente todas las modalidades disponibles.
- **Entradas**: Ninguna.
- **Resultado Esperado**:
  - El método debe devolver una lista de objetos `Modality` que representan todas las modalidades registradas en la base de datos.
  
#### Casos de prueba

1. **Modalidades disponibles**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: La lista debe contener todas las modalidades registradas en la base de datos.
   - **Resultado obtenido**: Se obtuvo correctamente la lista con todas las modalidades.

2. **Sin modalidades disponibles**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: Se debe devolver una lista vacía si no hay modalidades registradas.
   - **Resultado obtenido**: Se devolvió una lista vacía, como se esperaba.

---

### Prueba: Obtener modalidad por ID (Método: getModalityById)

- **Descripción**: Verificar que el método `getModalityById` devuelva correctamente una modalidad por su ID.
- **Entradas**:
  - `id`: ID de la modalidad a buscar (por ejemplo, `1`).
- **Resultado Esperado**:
  - El método debe devolver el objeto `Modality` correspondiente al `id` proporcionado, o `null` si no se encuentra la modalidad.
  
#### Casos de prueba

1. **Modalidad encontrada**:
   - **Entrada**: `id = 1`.
   - **Resultado esperado**: Se debe devolver la modalidad con `id = 1`.
   - **Resultado obtenido**: La modalidad con `id = 1` fue devuelta correctamente.

2. **Modalidad no encontrada**:
   - **Entrada**: `id = 9999` (ID no existente).
   - **Resultado esperado**: Se debe devolver `null` si no se encuentra la modalidad con el ID proporcionado.
   - **Resultado obtenido**: Se devolvió `null`, como se esperaba.

---

### Prueba: Crear modalidad (Método: createModality)

- **Descripción**: Verificar que el método `createModality` cree correctamente una nueva modalidad.
- **Entradas**:
  - `modality`: Objeto `Modality` con los detalles de la modalidad.
- **Resultado Esperado**:
  - La modalidad debe ser guardada en la base de datos con el campo `active` establecido como `false`.
  
#### Casos de prueba

1. **Creación de modalidad exitosa**:
   - **Entrada**: `modality = {name: "Modalidad 1"}`.
   - **Resultado esperado**: La modalidad debe ser guardada con `active = false`.
   - **Resultado obtenido**: La modalidad fue creada correctamente y su campo `active` es `false`.

2. **Error en creación de modalidad (datos inválidos)**:
   - **Entrada**: `modality = {name: ""}` (nombre vacío).
   - **Resultado esperado**: Se debe lanzar una excepción `RuntimeException` indicando que los datos de la modalidad no son válidos.
   - **Resultado obtenido**: Se lanzó la excepción `RuntimeException` con el mensaje adecuado.

---

### Prueba: Actualizar modalidad (Método: updateModality)

- **Descripción**: Verificar que el método `updateModality` actualice correctamente una modalidad existente.
- **Entradas**:
  - `modality`: Objeto `Modality` con los nuevos datos de la modalidad.
- **Resultado Esperado**:
  - Si la modalidad existe, debe ser actualizada correctamente en la base de datos.
  - Si la modalidad no existe, se debe lanzar una excepción `RuntimeException` con el mensaje adecuado.
  
#### Casos de prueba

1. **Actualización exitosa**:
   - **Entrada**: `modality = {id: 1, name: "Modalidad actualizada"}`.
   - **Resultado esperado**: La modalidad con `id = 1` debe ser actualizada con los nuevos datos.
   - **Resultado obtenido**: La modalidad fue actualizada correctamente.

2. **Modalidad no encontrada**:
   - **Entrada**: `modality = {id: 9999, name: "Modalidad actualizada"}` (ID no existente).
   - **Resultado esperado**: Se debe lanzar una excepción `RuntimeException` con el mensaje "Modalidad no encontrada para actualización".
   - **Resultado obtenido**: Se lanzó la excepción `RuntimeException` con el mensaje "Modalidad no encontrada para actualización".

---

### Prueba: Eliminar modalidad (Método: deleteModality)

- **Descripción**: Verificar que el método `deleteModality` elimine correctamente una modalidad, marcándola como inactiva (en lugar de eliminarla físicamente).
- **Entradas**:
  - `id`: ID de la modalidad a eliminar.
- **Resultado Esperado**:
  - Si la modalidad con el `id` proporcionado existe, debe ser marcada como inactiva (`active = false`) en la base de datos.
  - Si no se encuentra la modalidad, se debe lanzar una excepción `RuntimeException` con el mensaje adecuado.

#### Casos de prueba

1. **Eliminación exitosa**:
   - **Entrada**: `id = 1`.
   - **Resultado esperado**: La modalidad con `id = 1` debe ser marcada como inactiva (`active = false`).
   - **Resultado obtenido**: La modalidad fue correctamente marcada como inactiva.

2. **Modalidad no encontrada para eliminar**:
   - **Entrada**: `id = 9999` (ID no existente).
   - **Resultado esperado**: Se debe lanzar una excepción `RuntimeException` con el mensaje "Modalidad no encontrada para eliminación".
   - **Resultado obtenido**: Se lanzó la excepción `RuntimeException` con el mensaje "Modalidad no encontrada para eliminación".

---
