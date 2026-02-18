'use client'

import { useState } from 'react'

export default function Home() {
  const [count, setCount] = useState(0)

  return (
    <main className="flex min-h-screen flex-col items-center justify-center p-24">
      <div className="z-10 max-w-5xl w-full items-center justify-center font-mono text-sm">
        <h1 className="text-4xl font-bold text-center mb-4">
          AquaCentral
        </h1>
        <p className="text-center mb-8 text-lg">
          Welcome to AquaCentral - Your Spring Boot + Next.js Application
        </p>
        <div className="flex justify-center">
          <button
            onClick={() => setCount((count) => count + 1)}
            className="px-6 py-3 bg-blue-600 hover:bg-blue-700 text-white font-semibold rounded-lg transition-colors duration-200 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
          >
            count is {count}
          </button>
        </div>
      </div>
    </main>
  )
}
