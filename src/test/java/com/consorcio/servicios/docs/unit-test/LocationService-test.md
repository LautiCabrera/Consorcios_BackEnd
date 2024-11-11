# Documentación de Pruebas Unitarias para LocationServiceImpl

Este documento contiene los detalles de las pruebas unitarias realizadas para el servicio de residencias.

## Pruebas

### Prueba: Obtener ubicaciones por provincia (Método: getLocationsByProvince)

- **Descripción**: Verificar que el método `getLocationsByProvince` devuelva correctamente las ubicaciones asociadas a una provincia específica.
- **Entradas**:
  - `idProvince`: ID de la provincia (por ejemplo, `1`).
- **Resultado Esperado**:
  - El método debe devolver una lista de objetos `Location` asociados al `idProvince` proporcionado. Si no se encuentran ubicaciones para esa provincia, debe devolver una lista vacía.

#### Casos de prueba

1. **Ubicaciones encontradas para la provincia**:
   - **Entrada**: `idProvince = 1`.
   - **Resultado esperado**: El método debe devolver una lista de ubicaciones asociadas a la provincia con `id = 1`.
   - **Resultado obtenido**: Se obtuvo correctamente una lista de ubicaciones asociadas a la provincia con `id = 1`.

2. **Sin ubicaciones disponibles para la provincia**:
   - **Entrada**: `idProvince = 9999` (ID de provincia inexistente).
   - **Resultado esperado**: El método debe devolver una lista vacía si no hay ubicaciones asociadas a la provincia proporcionada.
   - **Resultado obtenido**: Se devolvió una lista vacía, como se esperaba.

---

