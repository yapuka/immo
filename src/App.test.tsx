import { render, screen } from '@testing-library/react'
import { describe, expect, it } from 'vitest'
import App from './App'

describe('App', () => {
  it('presents the starter stack', () => {
    render(<App />)
    expect(screen.getByRole('heading', { name: /point de départ/i })).toBeInTheDocument()
    expect(screen.getByText('outillage inclus')).toBeInTheDocument()
  })
})
