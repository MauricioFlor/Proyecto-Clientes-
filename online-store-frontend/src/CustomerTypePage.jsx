import { useEffect, useState } from 'react'
import api from './api'

function CustomerTypePage() {
  const [types, setTypes] = useState([])
  const [form, setForm] = useState({ customerTypeName: '', customerDescription: '' })
  const [editingId, setEditingId] = useState(null)

  useEffect(() => {
    api.get('/customer-types').then(res => setTypes(res.data))
  }, [])

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      if (editingId) {
        const res = await api.put(`/customer-types/${editingId}`, form)
        setTypes(types.map(t => t.id === editingId ? res.data : t))
        setEditingId(null)
      } else {
        const res = await api.post('/customer-types', form)
        setTypes([...types, res.data])
      }
      setForm({ customerTypeName: '', customerDescription: '' })
    } catch (error) {
      console.error('Error al guardar tipo de cliente:', error)
    }
  }

  const deleteType = async (id) => {
    try {
      await api.delete(`/customer-types/${id}`)
      setTypes(types.filter(t => t.id !== id))
      if (editingId === id) {
        setEditingId(null)
        setForm({ customerTypeName: '', customerDescription: '' })
      }
    } catch (error) {
      console.error('Error al eliminar tipo de cliente:', error)
    }
  }

  const startEdit = (type) => {
    setForm({
      customerTypeName: type.customerTypeName,
      customerDescription: type.customerDescription
    })
    setEditingId(type.id)
  }

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Tipos de Cliente</h2>
      <ul className="list-group mb-4">
        {types.map(t => (
          <li key={t.id} className="list-group-item d-flex justify-content-between align-items-center">
            <div>
              <strong>{t.customerTypeName}</strong>: {t.customerDescription}
            </div>
            <div className="btn-group">
              <button className="btn btn-warning btn-sm" onClick={() => startEdit(t)}>Editar</button>
              <button className="btn btn-danger btn-sm" onClick={() => deleteType(t.id)}>Eliminar</button>
            </div>
          </li>
        ))}
      </ul>

      <h3>{editingId ? 'Editar Tipo de Cliente' : 'Nuevo Tipo de Cliente'}</h3>
      <form onSubmit={handleSubmit} className="row g-3">
        <div className="col-md-5">
          <input
            className="form-control"
            placeholder="Nombre del tipo"
            value={form.customerTypeName}
            onChange={e => setForm({ ...form, customerTypeName: e.target.value })}
          />
        </div>
        <div className="col-md-5">
          <input
            className="form-control"
            placeholder="Descripción"
            value={form.customerDescription}
            onChange={e => setForm({ ...form, customerDescription: e.target.value })}
          />
        </div>
        <div className="col-md-2">
          <button type="submit" className="btn btn-primary w-100">
            {editingId ? 'Actualizar' : 'Agregar'}
          </button>
        </div>
      </form>
    </div>
  )
}

export default CustomerTypePage
