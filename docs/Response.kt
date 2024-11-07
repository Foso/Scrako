@Serializable
data class Response(

	@SerialName("extensions")
	val extensions: List<Any?>? = null,

	@SerialName("meta")
	val meta: Meta? = null,

	@SerialName("targets")
	val targets: List<TargetsItem?>? = null,

	@SerialName("monitors")
	val monitors: List<Any?>? = null
)

@Serializable
data class Broadcasts(

	@SerialName("input")
	val input: String? = null,

	@SerialName("paint")
	val paint: String? = null
)

@Serializable
data class Platform(

	@SerialName("name")
	val name: String? = null,

	@SerialName("url")
	val url: String? = null
)

@Serializable
data class CostumesItem(

	@SerialName("bitmapResolution")
	val bitmapResolution: Int? = null,

	@SerialName("dataFormat")
	val dataFormat: String? = null,

	@SerialName("assetId")
	val assetId: String? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("md5ext")
	val md5ext: String? = null,

	@SerialName("rotationCenterY")
	val rotationCenterY: Int? = null,

	@SerialName("rotationCenterX")
	val rotationCenterX: Int? = null
)

@Serializable
data class TargetsItem(

	@SerialName("variables")
	val variables: Variables? = null,

	@SerialName("comments")
	val comments: Comments? = null,

	@SerialName("visible")
	val visible: Boolean? = null,

	@SerialName("blocks")
	val blocks: Blocks? = null,

	@SerialName("isStage")
	val isStage: Boolean? = null,

	@SerialName("broadcasts")
	val broadcasts: Broadcasts? = null,

	@SerialName("costumes")
	val costumes: List<CostumesItem?>? = null,

	@SerialName("volume")
	val volume: Int? = null,

	@SerialName("rotationStyle")
	val rotationStyle: String? = null,

	@SerialName("sounds")
	val sounds: List<Any?>? = null,

	@SerialName("size")
	val size: Int? = null,

	@SerialName("draggable")
	val draggable: Boolean? = null,

	@SerialName("lists")
	val lists: Lists? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("x")
	val x: Int? = null,

	@SerialName("layerOrder")
	val layerOrder: Int? = null,

	@SerialName("y")
	val y: Int? = null,

	@SerialName("currentCostume")
	val currentCostume: Int? = null,

	@SerialName("direction")
	val direction: Int? = null,

	@SerialName("tempo")
	val tempo: Int? = null,

	@SerialName("videoTransparency")
	val videoTransparency: Int? = null,

	@SerialName("textToSpeechLanguage")
	val textToSpeechLanguage: Any? = null,

	@SerialName("videoState")
	val videoState: String? = null
)

@Serializable
data class Meta(

	@SerialName("agent")
	val agent: String? = null,

	@SerialName("semver")
	val semver: String? = null,

	@SerialName("vm")
	val vm: String? = null,

	@SerialName("platform")
	val platform: Platform? = null
)

@Serializable
data class Comments(
	val any: Any? = null
)

@Serializable
data class Blocks(

	@SerialName("a")
	val a: List<Int?>? = null,

	@SerialName("b")
	val b: List<Int?>? = null
)

@Serializable
data class Variables(

	@SerialName("4c54597f-ee50-4acf-9b08-627986f108d0")
	val jsonMember4c54597fEe504acf9b08627986f108d0: List<String?>? = null,

	@SerialName("75de33ef-6d97-44ce-9f86-dbb506bddff1")
	val jsonMember75de33ef6d9744ce9f86Dbb506bddff1: List<String?>? = null,

	@SerialName("7d32cf75-116e-44c5-b2f2-6fbdc868ad58")
	val jsonMember7d32cf75116e44c5B2f26fbdc868ad58: List<String?>? = null
)

@Serializable
data class Lists(
	val any: Any? = null
)

