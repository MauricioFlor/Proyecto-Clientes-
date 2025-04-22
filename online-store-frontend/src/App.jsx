import { useState } from 'react'
import CustomerPage from './CustomerPage.jsx'
import CustomerTypePage from './CustomerTypePage.jsx'

function App() {
  const [view, setView] = useState('customers')

  return (
    <div className="container mt-4">
      <h1 className="mb-4">Panel de Administración</h1>

      <div className="btn-group mb-4" role="group">
        <button
          className={`btn btn${view === 'customers' ? '' : '-outline'}-primary`}
          onClick={() => setView('customers')}
        >
          Clientes
        </button>
        <button
          className={`btn btn${view === 'types' ? '' : '-outline'}-secondary`}
          onClick={() => setView('types')}
        >
          Tipos de Cliente
        </button>
      </div>

      {view === 'customers' ? <CustomerPage /> : <CustomerTypePage />}
    </div>
  )
}

export default App
