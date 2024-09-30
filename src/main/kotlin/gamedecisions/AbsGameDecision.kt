package gamedecisions

abstract class AbsGameDecision(
    private val name: String,
    private val matchesOn: Regex
) : GameDecision {

    constructor(name: String, matchesOnPattern: String) :
            this(name, Regex(matchesOnPattern))

    override fun matches(name: String): Boolean =
        matchesOn.matches(name)

    override fun toString(): String = name
}