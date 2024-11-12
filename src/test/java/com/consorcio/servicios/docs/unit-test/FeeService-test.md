# Documentación de Pruebas Unitarias para FeeServiceImpl

Este documento contiene los detalles de las pruebas unitarias realizadas para el servicio de las tarifas.

## Pruebas

### Prueba: Obtener todas las tarifas (Método: getAllFee)

- **Descripción**: Verificar que el método `getAllFee` devuelva correctamente la lista de todas las tarifas disponibles.
- **Entradas**: Ninguna.
- **Resultado Esperado**:
  - El método debe devolver una lista de objetos `FeeDto` que contenga todas las tarifas almacenadas en la base de datos. Si no hay tarifas, debe devolver una lista vacía.

#### Casos de prueba

1. **Tarifas encontradas**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: El método debe devolver una lista de tarifas.
   - **Resultado obtenido**: Se obtuvo correctamente una lista de tarifas.

2. **Sin tarifas disponibles**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: Si no existen tarifas en la base de datos, el método debe devolver una lista vacía.
   - **Resultado obtenido**: Se devolvió una lista vacía, como se esperaba.

---
