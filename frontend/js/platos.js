// Script para la página de Platos

let categorias = [];

// Cargar datos al cargar la página
document.addEventListener('DOMContentLoaded', function() {
    cargarCategorias();
    cargarPlatos();
    
    // Manejar el envío del formulario
    document.getElementById('form-plato').addEventListener('submit', function(e) {
        e.preventDefault();
        registrarPlato();
    });
});

// Función para cargar categorías
async function cargarCategorias() {
    try {
        const response = await fetch(API_ENDPOINTS.categorias);
        categorias = await response.json();
        
        const selectCategoria = document.getElementById('categoria');
        const selectActualizar = document.getElementById('upd-categoria-plato');
        
        // Limpiar opciones existentes excepto la primera
        selectCategoria.innerHTML = '<option value="">Seleccionar categoría</option>';
        selectActualizar.innerHTML = '<option value="">Seleccionar categoría</option>';
        
        categorias.forEach(cat => {
            const option1 = document.createElement('option');
            option1.value = cat.idCategoria;
            option1.textContent = cat.nombre;
            selectCategoria.appendChild(option1);
            
            const option2 = document.createElement('option');
            option2.value = cat.idCategoria;
            option2.textContent = cat.nombre;
            selectActualizar.appendChild(option2);
        });
    } catch (error) {
        handleError(error);
    }
}

// Función para cargar todos los platos
async function cargarPlatos() {
    try {
        const response = await fetch(API_ENDPOINTS.platos);
        const platos = await response.json();
        
        const tbody = document.querySelector('table tbody');
        tbody.innerHTML = '';
        
        platos.forEach(plato => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${plato.idPlato}</td>
                <td>${plato.nombre}</td>
                <td>${plato.categoria ? plato.categoria.nombre : 'N/A'}</td>
                <td>${plato.precio.toFixed(2)}</td>
                <td>${plato.descripcion || 'N/A'}</td>
                <td><button class="btn btn-small" onclick="mostrarActualizarPlato(${plato.idPlato}, '${plato.nombre}', ${plato.categoria ? plato.categoria.idCategoria : 0}, ${plato.precio}, '${plato.descripcion || ''}')">Actualizar</button></td>
                <td><button class="btn btn-small" onclick="eliminarPlato(${plato.idPlato})">Eliminar</button></td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        handleError(error);
    }
}

// Función para agregar nueva categoría
async function agregarCategoria() {
    const nuevaCat = document.getElementById('nueva-categoria').value.trim();
    
    if (nuevaCat.length === 0) {
        alert('Por favor, ingresa un nombre para la categoría');
        return;
    }
    
    // Verificar si ya existe
    const existe = categorias.some(cat => cat.nombre.toLowerCase() === nuevaCat.toLowerCase());
    
    if (existe) {
        alert('La categoría ya existe.');
        return;
    }
    
    try {
        const response = await fetch(API_ENDPOINTS.categorias, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ nombre: nuevaCat })
        });
        
        if (response.ok) {
            alert('Categoría agregada exitosamente');
            document.getElementById('nueva-categoria').value = '';
            await cargarCategorias();
            // Seleccionar la nueva categoría
            const nuevaCategoria = categorias.find(cat => cat.nombre === nuevaCat);
            if (nuevaCategoria) {
                document.getElementById('categoria').value = nuevaCategoria.idCategoria;
            }
        } else {
            alert('Error al agregar la categoría');
        }
    } catch (error) {
        handleError(error);
    }
}

// Función para registrar un nuevo plato
async function registrarPlato() {
    const nombre = document.getElementById('nombre-plato').value;
    const categoriaId = document.getElementById('categoria').value;
    const precio = parseFloat(document.getElementById('precio').value);
    const descripcion = document.getElementById('descripcion').value;
    
    if (!categoriaId) {
        alert('Por favor, selecciona una categoría');
        return;
    }
    
    const nuevoPlato = {
        nombre: nombre,
        categoria: { idCategoria: parseInt(categoriaId) },
        precio: precio,
        descripcion: descripcion
    };
    
    try {
        const response = await fetch(API_ENDPOINTS.platos, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoPlato)
        });
        
        if (response.ok) {
            alert('Plato registrado exitosamente');
            document.getElementById('form-plato').reset();
            cargarPlatos();
        } else {
            alert('Error al registrar el plato');
        }
    } catch (error) {
        handleError(error);
    }
}

// Función para mostrar el modal de actualización
function mostrarActualizarPlato(id, nombre, categoriaId, precio, descripcion) {
    document.getElementById('modal-actualizar-plato').style.display = 'flex';
    document.getElementById('upd-id-plato').value = id;
    document.getElementById('upd-nombre-plato').value = nombre;
    document.getElementById('upd-categoria-plato').value = categoriaId;
    document.getElementById('upd-precio-plato').value = precio;
}

// Función para cerrar el modal
function cerrarActualizarPlato() {
    document.getElementById('modal-actualizar-plato').style.display = 'none';
}

// Función para guardar la actualización
async function guardarActualizacionPlato(e) {
    e.preventDefault();
    
    const id = document.getElementById('upd-id-plato').value;
    const nombre = document.getElementById('upd-nombre-plato').value;
    const categoriaId = document.getElementById('upd-categoria-plato').value;
    const precio = parseFloat(document.getElementById('upd-precio-plato').value);
    
    if (!categoriaId) {
        alert('Por favor, selecciona una categoría');
        return;
    }
    
    const platoActualizado = {
        nombre: nombre,
        categoria: { idCategoria: parseInt(categoriaId) },
        precio: precio,
        descripcion: ''
    };
    
    try {
        const response = await fetch(`${API_ENDPOINTS.platos}/${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(platoActualizado)
        });
        
        if (response.ok) {
            alert('Plato actualizado exitosamente');
            cerrarActualizarPlato();
            cargarPlatos();
        } else {
            alert('Error al actualizar el plato');
        }
    } catch (error) {
        handleError(error);
    }
}

// Función para eliminar un plato
async function eliminarPlato(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este plato?')) {
        try {
            const response = await fetch(`${API_ENDPOINTS.platos}/${id}`, {
                method: 'DELETE'
            });
            
            if (response.ok || response.status === 204) {
                alert('Plato eliminado exitosamente');
                cargarPlatos();
            } else if (response.status === 409) {
                const mensaje = await response.text();
                alert(mensaje);
            } else {
                alert('Error al eliminar el plato');
            }
        } catch (error) {
            handleError(error);
        }
    }
}

// Funciones para manejar insumos en el formulario (simplificado)
var contadorInsumos = 1;
function agregarInsumoPlato() {
    contadorInsumos++;
    var listaInsumos = document.getElementById('lista-insumos');
    var nuevoInsumo = document.createElement('div');
    nuevoInsumo.style.display = 'flex';
    nuevoInsumo.style.gap = '1rem';
    nuevoInsumo.style.marginBottom = '8px';
    nuevoInsumo.innerHTML = `
        <select name="insumo${contadorInsumos}" style="flex:2;">
            <option value="">Seleccionar Insumo</option>
        </select>
        <input type="number" name="cant${contadorInsumos}" step="0.01" min="0" placeholder="Cantidad" style="flex:1;">
    `;
    listaInsumos.appendChild(nuevoInsumo);
}

var contadorInsumosActualizar = 1;
function agregarInsumoActualizar() {
    contadorInsumosActualizar++;
    var listaInsumos = document.getElementById('upd-lista-insumos');
    var nuevoInsumo = document.createElement('div');
    nuevoInsumo.style.display = 'flex';
    nuevoInsumo.style.gap = '1rem';
    nuevoInsumo.style.marginBottom = '8px';
    nuevoInsumo.innerHTML = `
        <select name="upd-insumo${contadorInsumosActualizar}" style="flex:2;">
            <option value="">Seleccionar Insumo</option>
        </select>
        <input type="number" name="upd-cant${contadorInsumosActualizar}" step="0.01" min="0" placeholder="Cantidad" style="flex:1;">
    `;
    listaInsumos.appendChild(nuevoInsumo);
}
