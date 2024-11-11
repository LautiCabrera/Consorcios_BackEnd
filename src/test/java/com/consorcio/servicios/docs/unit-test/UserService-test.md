# Documentación de Pruebas Unitarias para UserServiceImpl

Este documento contiene los detalles de las pruebas unitarias realizadas para el servicio de usuarios.

## Pruebas

### Prueba: Actualización de usuario (Método: updateUser)

- **Descripción**: Verificar que el método `updateUser` actualice correctamente la información del usuario.
- **Entradas**:
  - `userId`: ID del usuario a actualizar (por ejemplo, 1).
  - `userDto`: Objeto `UserDto` con la nueva información del usuario, como `username`, `firstName`, `lastName`, `dni`, y `phone`.
- **Resultado Esperado**:
  - Si el usuario con `userId` existe, los datos deben ser actualizados correctamente.
  - Se debe guardar el usuario con los nuevos valores en la base de datos.
  - Si el usuario no existe, se debe lanzar una excepción `NoSuchElementException` con el mensaje "Usuario no encontrado".
  - El `dateUpdate` debe actualizarse con la fecha y hora actual.
  - El `idUserUpdate` debe ser el ID del usuario autenticado.

#### Casos de prueba

1. **Usuario encontrado y actualizado correctamente**:
   - **Entrada**: `userId = 1`, `userDto = {username: "newUser", firstName: "John", lastName: "Doe", dni: "123456789", phone: "1234567890"}`
   - **Resultado esperado**: El usuario con `id = 1` debe tener sus datos actualizados con la nueva información.
   - **Resultado obtenido**: La actualización fue exitosa. El usuario con `id = 1` tiene los nuevos datos. `dateUpdate` se actualizó correctamente con la fecha y hora actual. El `idUserUpdate` coincide con el ID del usuario autenticado.

2. **Usuario no encontrado (error)**:
   - **Entrada**: `userId = 9999` (ID que no existe).
   - **Resultado esperado**: Se debe lanzar una excepción `NoSuchElementException` con el mensaje "Usuario no encontrado".
   - **Resultado obtenido**: Se lanzó la excepción `NoSuchElementException` con el mensaje "Usuario no encontrado" como se esperaba.

---

### Prueba: Cambio de estado de usuario (Método: changeUserStatus)

- **Descripción**: Verificar que el método `changeUserStatus` cambie correctamente el estado de un usuario.
- **Entradas**:
  - `idUser`: ID del usuario cuyo estado se va a cambiar.
  - `status`: Nuevo estado del usuario, que es de tipo `UserStatus` (por ejemplo, `ACTIVO`, `INACTIVO`).
- **Resultado Esperado**:
  - Si el usuario con `idUser` existe, el estado del usuario debe cambiar al nuevo valor de `status`.
  - Si el usuario no existe, se debe lanzar una excepción `NoSuchElementException` con el mensaje "Usuario no encontrado".
  - El campo `idUserUpdate` debe ser actualizado con el ID del usuario autenticado.

#### Casos de prueba

1. **Cambio de estado exitoso**:
   - **Entrada**: `idUser = 1`, `status = UserStatus.INACTIVO`.
   - **Resultado esperado**: El estado del usuario con `idUser = 1` debe ser cambiado a `INACTIVO`.
   - **Resultado obtenido**: El estado del usuario con `idUser = 1` se cambió correctamente a `INACTIVO`. El `idUserUpdate` se actualizó con el ID del usuario autenticado.

2. **Usuario no encontrado (error)**:
   - **Entrada**: `idUser = 9999`, `status = UserStatus.ACTIVO`.
   - **Resultado esperado**: Se debe lanzar una excepción `NoSuchElementException` con el mensaje "Usuario no encontrado".
   - **Resultado obtenido**: Se lanzó la excepción `NoSuchElementException` con el mensaje "Usuario no encontrado" como se esperaba.

---

### Prueba: Obtener todos los usuarios (Método: getAllUsers)

- **Descripción**: Verificar que el método `getAllUsers` devuelva correctamente la lista de todos los usuarios.
- **Entradas**: Ninguna.
- **Resultado Esperado**:
  - La lista de usuarios debe contener todos los usuarios registrados.
  - Si la lista está vacía, se debe devolver una lista vacía.
  - La lista debe excluir el primer usuario (según la implementación del método).

#### Casos de prueba

1. **Usuarios existentes**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: Se debe devolver una lista de usuarios, excluyendo el primer elemento (como se implementa en el método).
   - **Resultado obtenido**: Se devolvió una lista de usuarios que excluye al primer usuario (como se esperaba). Los usuarios en la lista coinciden con los datos registrados.

2. **Lista vacía (sin usuarios)**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: Se debe devolver una lista vacía.
   - **Resultado obtenido**: Se devolvió una lista vacía, como se esperaba.

3. **Caso de usuarios con datos**:
   - **Entrada**: Ninguna.
   - **Resultado esperado**: Verificar que los usuarios devueltos contengan los datos correctos y que la lista excluya el primer usuario.
   - **Resultado obtenido**: Los datos de los usuarios son correctos. La lista excluye correctamente al primer usuario.

---

### Prueba: Obtener usuarios activos por rol y estado (Método: getUsersActives)

- **Descripción**: Verificar que el método `getUsersActives` devuelva los usuarios activos con el rol y estado correctos.
- **Entradas**:
  - `role`: Rol de los usuarios a buscar (por ejemplo, `ADMIN`, `USER`).
  - `status`: Estado de los usuarios a buscar (por ejemplo, `ACTIVO`, `INACTIVO`).
- **Resultado Esperado**:
  - La lista debe devolver solo los usuarios con el rol y estado proporcionados.
  - Si no hay usuarios con el rol y estado indicados, la lista debe estar vacía.

#### Casos de prueba:

1. **Usuarios activos con rol ADMIN**:
   - **Entrada**: `role = Role.ADMIN`, `status = UserStatus.ACTIVO`.
   - **Resultado esperado**: La lista debe devolver los usuarios con rol `ADMIN` y estado `ACTIVO`.
   - **Resultado obtenido**: La lista contiene correctamente los usuarios con rol `ADMIN` y estado `ACTIVO`.

2. **Usuarios activos con rol USER**:
   - **Entrada**: `role = Role.USER`, `status = UserStatus.ACTIVO`.
   - **Resultado esperado**: La lista debe devolver los usuarios con rol `USER` y estado `ACTIVO`.
   - **Resultado obtenido**: La lista contiene correctamente los usuarios con rol `USER` y estado `ACTIVO`.

3. **Usuarios inactivos**:
   - **Entrada**: `role = Role.USER`, `status = UserStatus.INACTIVO`.
   - **Resultado esperado**: La lista debe devolver los usuarios con rol `USER` y estado `INACTIVO`.
   - **Resultado obtenido**: La lista contiene correctamente los usuarios con rol `USER` y estado `INACTIVO`.

4. **No hay usuarios con ese rol y estado**:
   - **Entrada**: `role = Role.ADMIN`, `status = UserStatus.INACTIVO`.
   - **Resultado esperado**: La lista debe estar vacía si no hay usuarios con el rol y estado indicados.
   - **Resultado obtenido**: La lista está vacía, ya que no hay usuarios con rol `ADMIN` y estado `INACTIVO`.