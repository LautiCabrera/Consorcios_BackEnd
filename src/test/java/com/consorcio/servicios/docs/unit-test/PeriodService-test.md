# Documentación de Pruebas Unitarias para PeriodServiceImpl

Este documento contiene los detalles de las pruebas unitarias realizadas para el servicio de periodos.

## Pruebas

### Prueba: Obtener todas las modalidades (Método: getAllModalities)

- **Descripción**: Verificar que el método `getAllModalities` devuelva correctamente todas las modalidades disponibles.
- **Entradas**: Ninguna.
- **Resultado Esperado**:
  - El método debe devolver una lista de objetos `Period` que representan todas las modalidades registradas en la base de datos.
  
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

### Prueba: Obtener períodos por ID de modalidad (Método: getPeriodByModalityId)

- **Descripción**: Verificar que el método `getPeriodByModalityId` devuelva los períodos correspondientes a una modalidad específica.
- **Entradas**:
  - `idModality`: ID de la modalidad para buscar los períodos (por ejemplo, `1`).
- **Resultado Esperado**:
  - El método debe devolver una lista de objetos `ReadPeriodDto` que correspondan a los períodos de la modalidad con el `idModality` proporcionado.
  
#### Casos de prueba

1. **Períodos encontrados para una modalidad**:
   - **Entrada**: `idModality = 1`.
   - **Resultado esperado**: La lista debe contener los períodos asociados a la modalidad con `idModality = 1`.
   - **Resultado obtenido**: La lista contiene los períodos correspondientes a la modalidad con `idModality = 1`.

2. **No se encuentran períodos para una modalidad**:
   - **Entrada**: `idModality = 9999` (ID de modalidad no existente).
   - **Resultado esperado**: Se debe devolver una lista vacía si no se encuentran períodos para la modalidad.
   - **Resultado obtenido**: Se devolvió una lista vacía, como se esperaba.

---

### Prueba: Obtener períodos activos (Método: getPeriodsActives)

- **Descripción**: Verificar que el método `getPeriodsActives` devuelva correctamente los períodos activos.
- **Entradas**: Ninguna.
- **Resultado Esperado**:
  - El método debe devolver una lista de objetos `ReadPeriodDto` con los períodos activos.
  
#### Casos de prueba

1. **Períodos activos disponibles**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: La lista debe contener los períodos activos.
   - **Resultado obtenido**: Se devolvió correctamente la lista de períodos activos.

2. **Sin períodos activos disponibles**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: Se debe devolver una lista vacía si no hay períodos activos.
   - **Resultado obtenido**: Se devolvió una lista vacía, como se esperaba.

---

### Prueba: Crear período (Método: createPeriod)

- **Descripción**: Verificar que el método `createPeriod` cree correctamente un nuevo período.
- **Entradas**:
  - `period`: Objeto `Period` con los detalles del nuevo período.
- **Resultado Esperado**:
  - Si los datos del `period` son válidos, se debe crear un nuevo período en la base de datos.
  - Si los datos del `period` son inválidos, se debe lanzar una excepción `RuntimeException` con el mensaje adecuado.

#### Casos de prueba

1. **Creación de período exitosa**:
   - **Entrada**: `period = {name: "Período 1", isActive: true}`.
   - **Resultado esperado**: El nuevo período debe ser creado y registrado en la base de datos.
   - **Resultado obtenido**: El período fue creado correctamente en la base de datos.

2. **Error en creación de período (datos inválidos)**:
   - **Entrada**: `period = {name: "", isActive: true}` (nombre vacío).
   - **Resultado esperado**: Se debe lanzar una excepción `RuntimeException` indicando que los datos del período no son válidos.
   - **Resultado obtenido**: Se lanzó la excepción `RuntimeException` con el mensaje adecuado.

---

### Prueba: Actualizar período (Método: updatePeriod)

- **Descripción**: Verificar que el método `updatePeriod` actualice correctamente un período existente.
- **Entradas**:
  - `period`: Objeto `Period` con los nuevos datos del período.
- **Resultado Esperado**:
  - Si el período existe, sus datos deben ser actualizados correctamente.
  - Si no se encuentra el período, se debe lanzar una excepción `RuntimeException` con el mensaje adecuado.
  
#### Casos de prueba

1. **Actualización exitosa**:
   - **Entrada**: `period = {id: 1, name: "Período actualizado", isActive: true}`.
   - **Resultado esperado**: El período con `id = 1` debe ser actualizado con los nuevos datos.
   - **Resultado obtenido**: El período fue actualizado correctamente en la base de datos.

2. **Período no encontrado**:
   - **Entrada**: `period = {id: 9999, name: "Período actualizado", isActive: true}` (ID no existente).
   - **Resultado esperado**: Se debe lanzar una excepción `RuntimeException` con el mensaje "Período no encontrado para actualización".
   - **Resultado obtenido**: Se lanzó la excepción `RuntimeException` con el mensaje "Período no encontrado para actualización".

---

### Prueba: Eliminar período (Método: deletePeriod)

- **Descripción**: Verificar que el método `deletePeriod` elimine correctamente un período existente.
- **Entradas**:
  - `idPeriod`: ID del período a eliminar.
- **Resultado Esperado**:
  - Si el período con `idPeriod` existe, debe ser eliminado de la base de datos.
  - Si no se encuentra el período, se debe lanzar una excepción `RuntimeException` con el mensaje adecuado.

#### Casos de prueba

1. **Eliminación exitosa**:
   - **Entrada**: `idPeriod = 1`.
   - **Resultado esperado**: El período con `idPeriod = 1` debe ser eliminado correctamente de la base de datos.
   - **Resultado obtenido**: El período fue eliminado correctamente de la base de datos.

2. **Período no encontrado para eliminar**:
   - **Entrada**: `idPeriod = 9999` (ID no existente).
   - **Resultado esperado**: Se debe lanzar una excepción `RuntimeException` con el mensaje "Período no encontrado para eliminación".
   - **Resultado obtenido**: Se lanzó la excepción `RuntimeException` con el mensaje "Período no encontrado para eliminación".

---
