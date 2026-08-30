import { useState } from 'react'

const stack = ['React 19', 'Vite', 'Tailwind v4', 'Storybook']

function App() {
  const [isReady, setIsReady] = useState(false)

  return (
    <main className="min-h-screen overflow-hidden bg-[#f2efe7] px-6 py-8 text-[#17211b] sm:px-10 lg:px-16">
      <div className="mx-auto flex min-h-[calc(100vh-4rem)] max-w-6xl flex-col justify-between">
        <header className="flex items-center justify-between border-b border-[#17211b]/20 pb-5">
          <span className="font-mono text-sm font-medium tracking-[0.18em] uppercase">
            immo / starter
          </span>
          <span className="rounded-full border border-[#17211b]/25 px-3 py-1 font-mono text-xs">
            v0.1.0
          </span>
        </header>
        <section className="grid gap-12 py-20 lg:grid-cols-[1.3fr_0.7fr] lg:items-end">
          <div>
            <p className="mb-6 font-mono text-sm text-[#c5522c]">[ ready to build ]</p>
            <h1 className="max-w-4xl text-6xl leading-[0.92] font-semibold tracking-[-0.06em] sm:text-8xl">
              Un point de départ qui tient la route.
            </h1>
            <p className="mt-8 max-w-xl text-lg leading-relaxed text-[#17211b]/70">
              Un starter React 19 soigneusement équipé pour avancer vite, garder le code lisible et
              documenter les composants dès le premier jour.
            </p>
          </div>
          <aside className="border-l-2 border-[#c5522c] pl-6">
            <p className="font-mono text-xs tracking-[0.16em] text-[#17211b]/55 uppercase">
              outillage inclus
            </p>
            <ul className="mt-5 space-y-3 font-mono text-sm">
              {stack.map((item) => (
                <li key={item}>→ {item}</li>
              ))}
            </ul>
            <button
              className="mt-8 cursor-pointer bg-[#c5522c] px-5 py-3 font-mono text-sm font-medium text-white transition-transform hover:-translate-y-1"
              onClick={() => setIsReady((value) => !value)}
              type="button"
            >
              {isReady ? 'Tout est prêt ✓' : 'Tester le starter'}
            </button>
          </aside>
        </section>
        <footer className="flex flex-col gap-4 border-t border-[#17211b]/20 pt-5 font-mono text-xs text-[#17211b]/60 sm:flex-row sm:justify-between">
          <span>src/App.tsx</span>
          <span>npm run dev · npm run storybook</span>
        </footer>
      </div>
    </main>
  )
}

export default App
