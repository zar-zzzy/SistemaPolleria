// Script para la página de Insumos

// Cargar insumos al cargar la página
document.addEventListener('DOMContentLoaded', function() {
    cargarInsumos();
    
    // Manejar el envío del formulario
    document.getElementById('form-insumo').addEventListener('submit', function(e) {
        e.preventDefault();
        registrarInsumo();
    });
});

// Función para cargar todos los insumos
async function cargarInsumos() {
    try {
        const response = await fetch(API_ENDPOINTS.insumos);
        const insumos = await response.json();
        
        const tbody = document.querySelector('table tbody');
        tbody.innerHTML = '';
        
        insumos.forEach(insumo => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${insumo.id}</td>
                <td>${insumo.nombre}</td>
                <td>${insumo.unidadMedida || 'N/A'}</td>
                <td>${insumo.stock}</td>
                <td><button class="btn btn-small" onclick="mostrarActualizarInsumo(${insumo.id}, '${insumo.nombre}', '${insumo.unidadMedida}', ${insumo.stock})">Actualizar</button></td>
                <td><button class="btn btn-small" onclick="eliminarInsumo(${insumo.id})">Eliminar</button></td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        handleError(error);
    }
}

// Función para registrar un nuevo insumo
async function registrarInsumo() {
    const nombre = document.getElementById('nombre').value;
    const unidadMedida = document.getElementById('unidad').value;
    const stock = parseInt(document.getElementById('cantidad').value);
    
    const nuevoInsumo = {
        nombre: nombre,
        unidadMedida: unidadMedida,
        stock: stock
    };
    
    try {
        const response = await fetch(API_ENDPOINTS.insumos, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoInsumo)
        });
        
        if (response.ok) {
            alert('Insumo registrado exitosamente');
            document.getElementById('form-insumo').reset();
            cargarInsumos();
        } else {
            alert('Error al registrar el insumo');
        }
    } catch (error) {
        handleError(error);
    }
}

// Función para mostrar el modal de actualización
function mostrarActualizarInsumo(id, nombre, unidad, cantidad) {
    document.getElementById('modal-actualizar-insumo').style.display = 'flex';
    document.getElementById('upd-id').value = id;
    document.getElementById('upd-nombre').value = nombre;
    document.getElementById('upd-unidad').value = unidad;
    document.getElementById('upd-cantidad').value = cantidad;
}

// Función para cerrar el modal
function cerrarActualizarInsumo() {
    document.getElementById('modal-actualizar-insumo').style.display = 'none';
}

// Función para guardar la actualización
async function guardarActualizacionInsumo(e) {
    e.preventDefault();
    
    const id = document.getElementById('upd-id').value;
    const nombre = document.getElementById('upd-nombre').value;
    const unidadMedida = document.getElementById('upd-unidad').value;
    const stock = parseInt(document.getElementById('upd-cantidad').value);
    
    const insumoActualizado = {
        nombre: nombre,
        unidadMedida: unidadMedida,
        stock: stock
    };
    
    try {
        const response = await fetch(`${API_ENDPOINTS.insumos}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(insumoActualizado)
        });
        
        if (response.ok) {
            alert('Insumo actualizado exitosamente');
            cerrarActualizarInsumo();
            cargarInsumos();
        } else {
            alert('Error al actualizar el insumo');
        }
    } catch (error) {
        handleError(error);
    }
}

// Función para eliminar un insumo
async function eliminarInsumo(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este insumo?')) {
        try {
            const response = await fetch(`${API_ENDPOINTS.insumos}/${id}`, {
                method: 'DELETE'
            });
            
            if (response.ok || response.status === 204) {
                alert('Insumo eliminado exitosamente');
                cargarInsumos();
            } else if (response.status === 409) {
                const mensaje = await response.text();
                alert(mensaje);
            } else {
                alert('Error al eliminar el insumo');
            }
        } catch (error) {
            handleError(error);
        }
    }
}
