package br.com.eliascoelho911.habitos.model

import org.joda.time.DateTimeConstants.*

class Dias(private val dias: List<Int>) {
    fun tem(dia: Int) = dias.contains(dia)

    class Builder {
        private val dias = mutableListOf<Int>()

        fun seg(): Builder {
            dias.add(MONDAY)
            return this
        }

        fun ter(): Builder {
            dias.add(TUESDAY)
            return this
        }

        fun qua(): Builder {
            dias.add(WEDNESDAY)
            return this
        }

        fun qui(): Builder {
            dias.add(THURSDAY)
            return this
        }

        fun sex(): Builder {
            dias.add(FRIDAY)
            return this
        }

        fun sab(): Builder {
            dias.add(SATURDAY)
            return this
        }

        fun dom(): Builder {
            dias.add(SUNDAY)
            return this
        }

        fun cria() = Dias(dias)
    }
}
