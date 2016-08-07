package lb.dmag.util

/**
 * @author Leonid Bushuev
 **/
data class Couple<T>
(
    val a: T,
    val b: T
)
{
    val twin = a == b

    infix fun<Y> map(f: (x:T) -> Y): Couple<Y> = Couple(f(a), f(b))

    override fun toString() = """$a,$b"""
}