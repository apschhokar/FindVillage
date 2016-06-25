
public double compareStuff(Profile userProfile, Profile locationProfile){
	ArrayList userProfileList = new ArrayList(20);
	ArrayList locationProfileList = new ArrayList(20);

	//Collect percentages for each characterstic for the user
	for (TraitTreeNode category : userProfile.tree.children) {
	           for (TraitTreeNode superTrait: category.children)
	           		if (superTrait.percentage != null)
	           			userProfileList.add(superTrait.percentage);
	           		for (TraitTreeNode subTrait: superTrait.children) {
	           			if (subTrait.percentage != null)
	           				userProfileList.add(subTrait.percentage);
		           		for (TraitTreeNode trait: subTrait.children){
		           			if (trait.percentage != null)
		           				userProfileList.add(trait.percentage)
		           	 	}
	           }
	};

	//Collect percentages for each characterstic for the user
	for (TraitTreeNode category : locationProfile.tree.children) {
	           for (TraitTreeNode superTrait: category.children)
	           		if (superTrait.percentage != null)
	           			locationProfileList.add(superTrait.percentage);
	           		for (TraitTreeNode subTrait: superTrait.children) {
	           			if (subTrait.percentage != null)
	           				locationProfileList.add(subTrait.percentage);
		           		for (TraitTreeNode trait: subTrait.children){
		           			if (trait.percentage != null)
		           				locationProfileList.add(trait.percentage)
		           		}
	           }
	};
	 return getCosineSimilarity(userProfileList, locationProfileList);
}

private double getCosineSimilarity(ArrayList userProfileList, ArrayList locationProfileList) {
	int n = min(userProfileList.length(), locationProfileList.length());
	double numerator = 0.0;
	double userDenom = 0.0;
	double locationDenom = 0.0;
	for (int i = 0; i < n, i++) {
		numerator += userProfileList[i] * locationProfileList[i];
		userDenom += userProfileList[i] * userProfileList[i];
		locationDenom += locationProfileList[i] * locationProfileList[i];
	}

	double denom = math.sqrt(userDenom) * math.sqrt(locationDenom);
	return numerator / denom;
}