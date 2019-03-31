# NETBuilder-Assessment

Alphabet Position Replacement

This class has only one method that simply takes any string and returns ONLY letters after converting them to their positions in the alphabet.


Family Tree

This class has two lists, two hashmaps and 7 methods to access and update the stored data.

The lists store names that are given a gender with any of the setters. The male and female setters can only add names to the lists if they arent already in it, they also use already existing information, such as parent relations to set the gender for multiple names at once. The gender getters simply return whether or not that name has the gender in question assigned.

The parent and children getters simply return the list of names that have the parent or child relation set to that name. If none exist they return empty lists. The parent setter has the most functionality in it. It checks whether you are trying to set someone as a parent of himself, additionally it recursively checks if the child already has the parent's name down in the ancestry tree. Then if all passes it proceeds to create or update an entry in the hashmap for that child, at the same time inferring the gender of the new parent if the previous one was already set. Additionally if you attempt to set a third parent here it will similarly return false. After that it also creates or updates the children hashmap for the entry of this parent.



Both of these classes were unit tested to check if they satisfied the conditions provided in the task.
